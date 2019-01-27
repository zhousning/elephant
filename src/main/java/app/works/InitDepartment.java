package app.works;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.models.Center;
import app.models.Department;
import app.models.ExacctOne;
import app.models.ExacctThree;
import app.models.ExacctTwo;
import app.services.CenterService;
import app.services.DepartmentService;
import app.services.ExacctOneService;
import app.services.ExacctThreeService;
import app.services.ExacctTwoService;

public class InitDepartment {
	public static void init(String filePath, CenterService centerService, DepartmentService departmentService) {
		List<Map<String, String>> list = ParseExcel.parseExcelContentFromPath(filePath);
		
		List<Center> centers = centerService.findAll();
		Map<String, Center> centerMap = new LinkedHashMap<String, Center>();
		for (Center center: centers) {
			centerMap.put(center.getName(), center);
		}

		List<Department> departments = departmentService.findAll();
		Map<String, Department> depMap = new LinkedHashMap<String, Department>();
		for (Department dpmt : departments) {
			depMap.put(dpmt.getName(), dpmt);
		}
		
		for (Map<String, String> map : list) {
			String centerName = map.get("center").trim();
			String departmentName = map.get("department").trim();

			Center center = centerMap.get(centerName);
			if (center == null) {
				center = new Center(centerName);
				centerService.save(center);
				centerMap.put(center.getName(), center);
			}

			Department department = depMap.get(departmentName);
			if (department == null) {
				department = new Department(departmentName);
				department.setCenter(center);
				departmentService.save(department);
				depMap.put(department.getName(), department);
			}

		}
	}
}
