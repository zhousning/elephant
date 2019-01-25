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

import app.models.Center;
import app.models.Department;
import app.services.CenterService;
import app.services.DepartmentService;


@Controller
@RequestMapping("/centers")
public class CenterController extends BaseController {

	@Autowired
	CenterService centerService;
	@Autowired
	DepartmentService departmentService;
	
	
	@ModelAttribute
	public void getcenter(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("center", centerService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("centers", centerService.findAll());
		return "centers/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("center", new Center());
		return "centers/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Center center = centerService.findById(id);
		prepareData(map, center);
		map.put("center", center);
		return "centers/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("center", centerService.findById(id));
		return "centers/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		centerService.deleteById(id);
		return "redirect:/centers/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Center center, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/centers/new";
		}
		
		setAssociate(center
		);
		
			
		centerService.save(center);
		return "redirect:/centers/" + center.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Center center, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, center);
			return "/centers/edit";
		}
		
		setAssociate(center
		);
		
		
		centerService.update(center);
		return "redirect:/centers/" + center.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, Center center) {
	}
	
	public void setAssociate(Center center
	) {
	}
	

}