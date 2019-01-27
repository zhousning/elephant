package app.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.models.Role;
import app.models.User;
import app.services.CenterService;
import app.services.DepartmentService;
import app.services.ExacctOneService;
import app.services.ExacctThreeService;
import app.services.ExacctTwoService;
import app.services.RoleService;
import app.services.UserService;
import app.works.InitDepartment;
import app.works.InitExacct;
import app.works.InitUser;

@Controller
@RequestMapping("/seeds")
public class SeedController  extends BaseController  {
	@Autowired
	ExacctOneService exacctOneService;
	@Autowired
	ExacctTwoService exacctTwoService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	CenterService centerService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/initdata")
	@ResponseBody
	public Map<String, String> initData(HttpServletRequest request) {
		initRole();
		initAdmin();
		String exacctPath = request.getSession().getServletContext().getRealPath("/static/xls/exacct.xlsx");
		initExacct(exacctPath);
		
		String departmentPath = request.getSession().getServletContext().getRealPath("/static/xls/department.xlsx");
		initDepartment(departmentPath);
		
		String userPath = request.getSession().getServletContext().getRealPath("/static/xls/user.xlsx");
		initUser(userPath);
		
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "success");
		return status;
	}
	
	public void initExacct(String filePath) {
		InitExacct.init(filePath, exacctOneService, exacctTwoService, exacctThreeService);
	}
	
	public void initDepartment(String filePath) {
		InitDepartment.init(filePath, centerService, departmentService);
	}
	
	public void initUser(String filePath) {
		InitUser.init(filePath, userService, roleService, departmentService);
	}
	
	private void initAdmin() {
		String hashAlgorithmName = "MD5";
		Object credentials = "admin@admin.com";
		Object salt = ByteSource.Util.bytes("admin@admin.com");
		int hashIterations = 1024;

		Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		User admin = new User("Admin", "1579999999", "admin@admin.com", password.toString());
		Role adminRole = roleService.findByName(messageSource.getMessage("roles.admin", null, null));
		//Role teacherRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);
		//roles.add(teacherRole);
		admin.setRoles(roles);
		userService.save(admin);
	}
	
	private void initRole() {
		Role adminRole = new Role(messageSource.getMessage("roles.admin", null, null));
		Role defaultRole = new Role(messageSource.getMessage("roles.default", null, null));

		roleService.save(adminRole);
		roleService.save(defaultRole);
	}

}
