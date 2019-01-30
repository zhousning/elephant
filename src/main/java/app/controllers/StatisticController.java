package app.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.models.Department;
import app.models.ExacctThree;
import app.models.Expense;
import app.models.Statistic;
import app.services.DepartmentService;
import app.services.ExacctThreeService;
import app.services.ExpenseService;
import app.services.StatisticService;
import app.works.ExcelWrite;
import app.works.ExportDoc;

@Controller
@RequestMapping("/statistics")
public class StatisticController extends BaseController {

	@Autowired
	StatisticService statisticService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	ExpenseService expenseService;

	@ModelAttribute
	public void getstatistic(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("statistic", statisticService.findById(id));
		}
	}

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("statistics", statisticService.findAll());
		return "statistics/index";
	}

	@RequestMapping("/random_selector")
	@ResponseBody
	public Map<String, Object> randomSelector(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "exacctId", required = false) Integer exacctId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean adminRole = adminRole();
		boolean leaderRole = leaderRole();		
		if (adminRole || leaderRole) {
			List<Statistic> allDepExacctCostPerMonth = statisticService.allDepExacctCostPerMonth(exacctId, start, end);
			List<Statistic> allCostPerMonth = statisticService.allCostPerMonth(start, end);
			List<Statistic> allCostPerDepByMonth = statisticService.allCostPerDepByMonth(start, end);
			List<Statistic> allCostPerDepByMonthAndExacct = statisticService.allCostPerDepByMonthAndExacct(exacctId, start,
					end);
			map.put("allDepExacctCostPerMonth", allDepExacctCostPerMonth);
			map.put("allCostPerMonth", allCostPerMonth);
			map.put("allCostPerDepByMonth", allCostPerDepByMonth);
			map.put("allCostPerDepByMonthAndExacct", allCostPerDepByMonthAndExacct);
		}
		if (adminRole || !leaderRole) {
			List<Statistic> depSumCostPerMonth = statisticService.depSumCostPerMonth(departmentId, start, end);
			List<Statistic> depExacctCostPerMonth = statisticService.depExacctCostPerMonth(departmentId, exacctId, start,
					end);
			map.put("depSumCostPerMonth", depSumCostPerMonth);
			map.put("depExacctCostPerMonth", depExacctCostPerMonth);
		}
		
		String exacctName = exacctThreeService.findById(exacctId).getName();
		String depName = departmentService.findById(departmentId).getName();
		map.put("exacct", exacctName);
		map.put("department", depName);
		return map;
	}

	@RequestMapping("/filterdata")
	@ResponseBody
	public Map<String, Object> randomSelector(
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Expense> expenses = expenseService.selectByConditions(departmentId, start, end);

		map.put("expenses", expenses);
		return map;
	}

	public Map<String, String> fileName(HttpSession session) throws Exception {
		String path = session.getServletContext().getRealPath("/download/");
		path = path.replaceAll("\\\\", "/");

		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		String fileName = uuid + ".xls";

		path = path + "/" + fileName;

		File destFile = new File(path);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("path", path);
		fileMap.put("filename", fileName);
		return fileMap;
	}

	@RequestMapping(value = "/{path}/export_xls")
	public ResponseEntity<byte[]> download(@PathVariable("path") String path,
			@RequestParam(value = "departmentId", required = false) Integer departmentId,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end, HttpSession session) throws Exception {
		String file = "";
		if (path.equals("allcost")) {
			file = exportAllDepCost(session, start, end);
		} else if (path.equals("departmentcost")) {
			file = exportDepCost(session, departmentId, start, end);
		} else if (path.equals("staffcost")) {
			file = exportAllByStaff(session, start, end);
		}
		ResponseEntity<byte[]> response = downloadProcess(file, session);
		return response;
	}
	
	public String exportAllByStaff(HttpSession session, String start, String end) throws Exception {
		List<ExacctThree> exacctThrees = exacctThreeService.findAll();
		String title[] = new String[exacctThrees.size() + 5];
		title[0] = "月份";
		title[1] = "工号";	
		title[2] = "姓名";
		title[3] = "中心/工厂";
		title[4] = "部门";
		title[5] = "总计";
		for (int i = 6; i < title.length; i++) {
			title[i] = exacctThrees.get(i-6).getName();
		}
		Map<String, String> fileMap = fileName(session);
		String file = fileMap.get("path");
		ExcelWrite.createExcel(file, "sheet1", title);

		Map<String, Object> costs = statisticService.allCostByStaff(start, end);
		List<Map> list = new ArrayList<Map>();
		for (Map.Entry<String, Object> entry : costs.entrySet()) {
			Map<String, String> map = new HashMap<String, String>();
			String staffid = entry.getKey();
			map.put("工号", staffid);
			Map<String, String> staff = (Map<String, String>) entry.getValue();
			for (Map.Entry<String, String> obj : staff.entrySet()) {
				map.put(obj.getKey(), obj.getValue());	
			}
			list.add(map);
		}
		ExcelWrite.writeToExcel(file, "sheet1", list);
		return fileMap.get("filename");
	}
	
	public String exportAllDepCost(HttpSession session, String start, String end) throws Exception {
		List<ExacctThree> exacctThrees = exacctThreeService.findAll();
		String title[] = new String[exacctThrees.size() + 4];
		title[0] = "中心/工厂";
		title[1] = "部门";
		title[2] = "人数";
		title[3] = "总计";
		for (int i = 4; i < title.length; i++) {
			title[i] = exacctThrees.get(i-4).getName();
		}
		Map<String, String> fileMap = fileName(session);
		String file = fileMap.get("path");
		ExcelWrite.createExcel(file, "sheet1", title);

		List<Map> list = new ArrayList<Map>();
		List<Department> departments = departmentService.findAll();
		for (Department department: departments) {
			String staffNum = statisticService.depStaffCount(department.getId());
			String depCostSum = statisticService.depCostSum(department.getId(), start, end);
			List<Statistic> statistics = statisticService.depExacctCostSum(department.getId(), start, end);

			Map<String, String> map = new HashMap<String, String>();
			map.put("中心/工厂", department.getCenter().getName());
			map.put("部门", department.getName());
			map.put("人数", staffNum);
			map.put("总计", depCostSum);
			for (Statistic statistic : statistics) {
				map.put(statistic.getLabel(), statistic.getSum());
			}
			list.add(map);
		}

		ExcelWrite.writeToExcel(file, "sheet1", list);
		return fileMap.get("filename");
	}
	
	public String exportDepCost(HttpSession session, Integer departmentId, String start, String end) throws Exception {
		List<ExacctThree> exacctThrees = exacctThreeService.findAll();
		String title[] = new String[exacctThrees.size() + 4];
		title[0] = "中心/工厂";
		title[1] = "部门";
		title[2] = "人数";
		title[3] = "总计";
		for (int i = 4; i < title.length; i++) {
			title[i] = exacctThrees.get(i-4).getName();
		}
		Map<String, String> fileMap = fileName(session);
		String file = fileMap.get("path");
		ExcelWrite.createExcel(file, "sheet1", title);

		Department department = departmentService.findById(departmentId);
		String staffNum = statisticService.depStaffCount(departmentId);
		String depCostSum = statisticService.depCostSum(departmentId, start, end);
		List<Statistic> statistics = statisticService.depExacctCostSum(departmentId, start, end);

		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("中心/工厂", department.getCenter().getName());
		map.put("部门", department.getName());
		map.put("人数", staffNum);
		map.put("总计", depCostSum);
		for (Statistic statistic : statistics) {
			map.put(statistic.getLabel(), statistic.getSum());
		}
		list.add(map);

		ExcelWrite.writeToExcel(file, "sheet1", list);
		return fileMap.get("filename");
	}
	
	public ResponseEntity<byte[]> downloadProcess(String file, HttpSession session) throws IOException {
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/download/" + file);
		body = new byte[in.available()];
		in.read(body);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=" + file);

		HttpStatus statusCode = HttpStatus.OK;

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}

	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("statistic", new Statistic());
		return "statistics/new";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Statistic statistic = statisticService.findById(id);
		map.put("statistic", statistic);
		return "statistics/edit";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("statistic", statisticService.findById(id));
		return "statistics/show";
	}

	@RequestMapping(value = "/{id}/destroy", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		statisticService.deleteById(id);
		return "redirect:/statistics/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Statistic statistic, Errors result, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/statistics/new";
		}

		statisticService.save(statistic);
		return "redirect:/statistics/" + statistic.getId().toString();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Statistic statistic, Errors result, Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/statistics/edit";
		}

		statisticService.update(statistic);
		return "redirect:/statistics/" + statistic.getId().toString();
	}

	private void prepareData(Map<String, Object> map, Statistic statistic) {
	}

}