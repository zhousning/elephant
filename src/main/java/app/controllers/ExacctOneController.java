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

import app.models.ExacctOne;
import app.models.ExacctTwo;
import app.services.ExacctOneService;
import app.services.ExacctTwoService;


@Controller
@RequestMapping("/exacctOnes")
public class ExacctOneController extends BaseController {

	@Autowired
	ExacctOneService exacctOneService;
	@Autowired
	ExacctTwoService exacctTwoService;
	
	
	@ModelAttribute
	public void getexacctOne(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("exacctOne", exacctOneService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("exacctOnes", exacctOneService.findAll());
		return "exacctOnes/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("exacctOne", new ExacctOne());
		return "exacctOnes/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		ExacctOne exacctOne = exacctOneService.findById(id);
		prepareData(map, exacctOne);
		map.put("exacctOne", exacctOne);
		return "exacctOnes/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("exacctOne", exacctOneService.findById(id));
		return "exacctOnes/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		exacctOneService.deleteById(id);
		return "redirect:/exacctOnes/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid ExacctOne exacctOne, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/exacctOnes/new";
		}
		
		setAssociate(exacctOne
		);
		
			
		exacctOneService.save(exacctOne);
		return "redirect:/exacctOnes/" + exacctOne.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid ExacctOne exacctOne, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, exacctOne);
			return "/exacctOnes/edit";
		}
		
		setAssociate(exacctOne
		);
		
		
		exacctOneService.update(exacctOne);
		return "redirect:/exacctOnes/" + exacctOne.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, ExacctOne exacctOne) {
	}
	
	public void setAssociate(ExacctOne exacctOne
	) {
	}
	

}