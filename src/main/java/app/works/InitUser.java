package app.works;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import app.models.Center;
import app.models.Department;
import app.models.Role;
import app.models.User;
import app.services.CenterService;
import app.services.DepartmentService;
import app.services.RoleService;
import app.services.UserService;
import net.bytebuddy.asm.Advice.This;

public class InitUser {
	public static void init(String filePath, UserService userService, RoleService roleService,
			DepartmentService departmentService) {
		List<Map<String, String>> list = ParseExcel.parseExcelContentFromPath(filePath);

		String roleStr = "staff";
		Role defaultRole = roleService.findByName(roleStr);

		List<Department> departments = departmentService.findAll();
		Map<String, Department> depMap = new LinkedHashMap<String, Department>();
		for (Department dpmt : departments) {
			depMap.put(dpmt.getName(), dpmt);
		}

		List<User> users = userService.findAll();
		Map<String, User> userMap = new LinkedHashMap<String, User>();
		for (User user : users) {
			userMap.put(user.getEmail(), user);
		}

		List<User> userList = new ArrayList<User>();
		for (Map<String, String> map : list) {
			String staffid = map.get("staffid").trim();
			staffid = staffid.substring(0, staffid.lastIndexOf("."));
			String staffname = map.get("staffname").trim();
			String departmentName = map.get("department").trim();

			User user = userMap.get(staffid);
			Department department = depMap.get(departmentName);

			if (user == null && department != null) {
				String principal = staffid;
				String hashAlgorithmName = "MD5";
				Object credentials = staffid;
				Object salt = ByteSource.Util.bytes(principal);
				int hashIterations = 1024;

				Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
				user = new User(staffid, staffname, password.toString());
				Set<Role> roles = new HashSet<Role>();
				roles.add(defaultRole);

				user.setRoles(roles);
				user.setDepartment(department);
				userList.add(user);
				//userService.save(user);
			}
		}
		userService.batchSave(userList);
	}
}
