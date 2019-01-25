package app.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
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

import app.models.Department;
import app.models.Center;
import app.models.User;
import app.models.Expense;
import app.services.DepartmentService;
import app.services.CenterService;
import app.services.UserService;
import app.services.ExpenseService;


@Controller
@RequestMapping("/departments")
public class DepartmentController extends BaseController {

	@Autowired
	DepartmentService departmentService;
	@Autowired
	CenterService centerService;
	@Autowired
	UserService userService;
	@Autowired
	ExpenseService expenseService;
	
	
	@ModelAttribute
	public void getdepartment(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("department", departmentService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("departments", departmentService.findAll());
		return "departments/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("department", new Department());
		return "departments/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Department department = departmentService.findById(id);
		prepareData(map, department);
		map.put("department", department);
		return "departments/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("department", departmentService.findById(id));
		return "departments/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		departmentService.deleteById(id);
		return "redirect:/departments/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Department department, Errors result, Map<String, Object> map
		, @RequestParam(value="center.id", required=false) Integer centerId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/departments/new";
		}
		
		setAssociate(department
		, centerId
		);
		
			
		departmentService.save(department);
		return "redirect:/departments/" + department.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Department department, Errors result, Map<String, Object> map
		, @RequestParam(value="center.id", required=false) Integer centerId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, department);
			return "/departments/edit";
		}
		
		setAssociate(department
		, centerId
		);
		
		
		departmentService.update(department);
		return "redirect:/departments/" + department.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, Department department) {
		map.put("centers", centerService.findAll());
	}
	
	public void setAssociate(Department department
		, Integer centerId
	) {
		if (centerId != null) {
			Center center = centerService.findById(centerId);
			department.setCenter(center);
		}
	}
	

}