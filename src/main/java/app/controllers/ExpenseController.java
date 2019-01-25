package app.controllers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
		map.put("exacctThrees", exacctThreeService.findAll());
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
		map.put("expense", expense);
		return "expenses/edit";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("expense", expenseService.findById(id));
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
			@RequestParam(value = "department.id", required = false) Integer departmentId, HttpServletRequest request,
			HttpServletResponse response) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, expense);
			return "/expenses/edit";
		}

		setAssociate(expense, exacctThreeId, departmentId);

		expenseService.update(expense);
		return "redirect:/expenses/" + expense.getId().toString();
	}

	@RequestMapping("/uploadExcel")
	public String uploadExcel(@RequestParam("excel") MultipartFile file,
			@RequestParam("exacctThree") String exacctThree) throws ParseException {
		List<Map<String, String>> list = ParseExcel.parseExcelContent(file);
		if (list == null) {
			return "expense/index";
		}
		Explain explain = null;
		for (Map<String, String> map : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(map.get("月份"));
			String staffid = map.get("工号");
			String staffname = map.get("姓名");
			String departmentName = map.get("部门");
			float sum = Float.parseFloat(map.get(exacctThree));

			map.remove(map.get("月份"));
			map.remove(map.get("工号"));
			map.remove(map.get("姓名"));
			map.remove(map.get("部门"));
			map.remove(map.get(exacctThree));

			String info = "";
			String explainTitle = "";
			for (Entry<String, String> entry : map.entrySet()) {
				explainTitle += entry.getKey() + "SplitLine";
				info += entry.getValue() + "SplitLine";
			}
			if (explain == null) {
				explain = explainService.findByName(explainTitle);
				if (explain == null) {
					explain = new Explain(explainTitle);
					explainService.save(explain);
				}
			}
			
			Department department = departmentService.findByName(departmentName);
			ExacctThree exacctThreeObj = exacctThreeService.findByName(exacctThree);
			Expense expense = new Expense(date, staffid, staffname, sum, info);
			expense.setDepartment(department);
			expense.setExacctThree(exacctThreeObj);
			expense.setExplain(explain);
			expenseService.save(expense);
		}	
		return "expenses/index";
	}

	private void prepareExplains(Expense expense, Map<String, Object> map) {

	}

	private void prepareData(Map<String, Object> map, Expense expense) {
		map.put("exacctThrees", exacctThreeService.findAll());
		map.put("departments", departmentService.findAll());
		if (expense != null) {
			prepareExplains(expense, map);
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