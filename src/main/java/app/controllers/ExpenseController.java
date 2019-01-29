package app.controllers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import antlr.StringUtils;
import app.models.ImageAttachment;
import app.services.ImageAttachmentService;

import app.models.Expense;
import app.models.ExacctThree;
import app.models.Department;
import app.models.User;
import app.models.Explain;
import app.services.ExpenseService;
import app.services.ExacctThreeService;
import app.services.DepartmentService;
import app.services.UserService;
import app.works.ParseExcel;
import app.services.ExplainService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController extends BaseController {

	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserService userService;
	@Autowired
	ExplainService explainService;

	@ModelAttribute
	public void getexpense(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("expense", expenseService.findById(id));
		}
	}

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		List<ExacctThree> exacctThrees = exacctThreeService.findAll();
		map.put("exacctThrees", exacctThrees);
		map.put("expenses", expenseService.findAll());
		return "expenses/index";
	}

	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		Expense expense = new Expense();
		map.put("expense", expense);
		return "expenses/new";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Expense expense = expenseService.findById(id);
		prepareData(map, expense);
		return "expenses/edit";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		Expense expense = expenseService.findById(id);
		prepareData(map, expense);
		return "expenses/show";
	}

	@RequestMapping(value = "/{id}/destroy", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		expenseService.deleteById(id);
		return "redirect:/expenses/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Expense expense, Errors result, Map<String, Object> map,
			@RequestParam(value = "exacctThree.id", required = false) Integer exacctThreeId,
			@RequestParam(value = "department.id", required = false) Integer departmentId, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/expenses/new";
		}

		setAssociate(expense, exacctThreeId, departmentId);

		expenseService.save(expense);
		return "redirect:/expenses/" + expense.getId().toString();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Expense expense, Errors result, Map<String, Object> map,
			@RequestParam(value = "exacctThree.id", required = false) Integer exacctThreeId,
			@RequestParam(value = "department.id", required = false) Integer departmentId, 
			@RequestParam(value = "explainNames", required = false) String[] explainNames,
			HttpServletRequest request,
			HttpServletResponse response) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, expense);
			return "/expenses/edit";
		}
		
		String info = "";
		for (int i=0; i< explainNames.length; i++) {
			info += explainNames[i] + "SplitLine";
		}
		expense.setInfo(info);
		setAssociate(expense, exacctThreeId, departmentId);

		expenseService.update(expense);
		return "redirect:/expenses/" + expense.getId().toString();
	}

	@RequestMapping("/uploadExcel")
	public String uploadExcel(@RequestParam("excel") MultipartFile file,
			@RequestParam("exacctThree") String exacctThreeName) {
		String fileName = file.getOriginalFilename();
		if (fileName.equals("")) {
			return "redirect:/expenses/index";
		}
		String extString = fileName.substring(fileName.lastIndexOf("."));
		List<Map<String, String>> list = null;
		if (".xls".equals(extString) || ".xlsx".equals(extString)) {
			list = ParseExcel.parseExcelContent(file);
		} else {
			return "redirect:/expenses/index";
		}
		if (list == null) {
			return "redirect:/expenses/index";
		}
		
		Map<String, Explain> explainMap = getExplainMap();
		Map<String, Department> departmentMap = getDepartmentMap();
		Map<String, ExacctThree> exacctThreeMap = getExacctThreeMap();
		
		Calendar calendar = new GregorianCalendar(1900,0,-1);  
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		for (Map<String, String> map : list) {
			Date date;
			try {
				String month = map.get("月份");
				month = month.substring(0, month.lastIndexOf("."));
				date = DateUtils.addDays(d,Integer.valueOf(month));
			} catch (Exception e) {
				date = new Date();
			}
			String staffid = map.get("工号");
			staffid = staffid == null ? "" : staffid.substring(0, staffid.lastIndexOf("."));
			String staffname = map.get("姓名");
			staffname = staffname == null ? "" : staffname;
			String departmentName = map.get("部门");
			
			float sum = 0;
			try {
				sum = Float.parseFloat(map.get(exacctThreeName));
			} catch (Exception e) {
				return "redirect:/expenses/index";
			}

			map.remove("月份");
			map.remove("工号");
			map.remove("姓名");
			map.remove("部门");
			map.remove("中心/工厂");
			map.remove(exacctThreeName);

			String info = "";
			String explainTitle = "";
			for (Entry<String, String> entry : map.entrySet()) {
				explainTitle += entry.getKey() + "SplitLine";
				String val = entry.getValue();
				val = val == null ? "" : entry.getValue();
				info += val + "SplitLine";
			}
			
			Explain explain = explainMap.get(explainTitle);
			if (explain == null) {
				explain = new Explain(explainTitle);
				explainService.save(explain);
				explainMap.put(explain.getName(), explain);
			}
			
			Department department = departmentMap.get(departmentName);
			ExacctThree exacctThree = exacctThreeMap.get(exacctThreeName);
			if (department != null && exacctThree != null) {
				Expense expense = new Expense(date, staffid, staffname, sum, info);
				expense.setDepartment(department);
				expense.setExacctThree(exacctThree);
				expense.setExplain(explain);
				expenseService.save(expense);
			}
		}
		return "redirect:/expenses/index";
	}
	
	
	public Map<String, Explain> getExplainMap() {
		List<Explain> explains = explainService.findAll();
		Map<String, Explain> explainMap = new HashMap<String, Explain>();
		for (Explain explain : explains) {
			explainMap.put(explain.getName(), explain);
		}
		return explainMap;
	}
	
	public Map<String, Department> getDepartmentMap() {
		List<Department> departments = departmentService.findAll();
		Map<String, Department> departmentMap = new HashMap<String, Department>();
		for (Department department : departments) {
			departmentMap.put(department.getName(), department);
		}
		return departmentMap;
	}
	
	public Map<String, ExacctThree> getExacctThreeMap() {
		List<ExacctThree> ExacctThrees = exacctThreeService.findAll();
		Map<String, ExacctThree> ExacctThreeMap = new HashMap<String, ExacctThree>();
		for (ExacctThree ExacctThree : ExacctThrees) {
			ExacctThreeMap.put(ExacctThree.getName(), ExacctThree);
		}
		return ExacctThreeMap;
	}

	private void prepareExplains(Expense expense, Map<String, Object> map) {

	}

	private void prepareData(Map<String, Object> map, Expense expense) {
		map.put("exacctThrees", exacctThreeService.findAll());
		map.put("departments", departmentService.findAll());
		map.put("expense", expense);
				
		if (expense != null) {
			Integer[] ids = expenseService.findExplainIdDeptIdById(expense.getId());
			Explain explain = explainService.findById(ids[0]);
			map.put("explains", explain.getName());
		}
	}

	public void setAssociate(Expense expense, Integer exacctThreeId, Integer departmentId) {
		if (exacctThreeId != null) {
			ExacctThree exacctThree = exacctThreeService.findById(exacctThreeId);
			expense.setExacctThree(exacctThree);
		}
		if (departmentId != null) {
			Department department = departmentService.findById(departmentId);
			expense.setDepartment(department);
		}
	}

}