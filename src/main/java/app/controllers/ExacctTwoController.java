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

import app.models.ExacctTwo;
import app.models.ExacctThree;
import app.models.ExacctOne;
import app.services.ExacctTwoService;
import app.services.ExacctThreeService;
import app.services.ExacctOneService;


@Controller
@RequestMapping("/exacctTwos")
public class ExacctTwoController extends BaseController {

	@Autowired
	ExacctTwoService exacctTwoService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	ExacctOneService exacctOneService;
	
	
	@ModelAttribute
	public void getexacctTwo(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("exacctTwo", exacctTwoService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("exacctTwos", exacctTwoService.findAll());
		return "exacctTwos/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("exacctTwo", new ExacctTwo());
		return "exacctTwos/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		ExacctTwo exacctTwo = exacctTwoService.findById(id);
		prepareData(map, exacctTwo);
		map.put("exacctTwo", exacctTwo);
		return "exacctTwos/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("exacctTwo", exacctTwoService.findById(id));
		return "exacctTwos/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		exacctTwoService.deleteById(id);
		return "redirect:/exacctTwos/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid ExacctTwo exacctTwo, Errors result, Map<String, Object> map
		, @RequestParam(value="exacctOne.id", required=false) Integer exacctOneId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/exacctTwos/new";
		}
		
		setAssociate(exacctTwo
		, exacctOneId
		);
		
			
		exacctTwoService.save(exacctTwo);
		return "redirect:/exacctTwos/" + exacctTwo.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid ExacctTwo exacctTwo, Errors result, Map<String, Object> map
		, @RequestParam(value="exacctOne.id", required=false) Integer exacctOneId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, exacctTwo);
			return "/exacctTwos/edit";
		}
		
		setAssociate(exacctTwo
		, exacctOneId
		);
		
		
		exacctTwoService.update(exacctTwo);
		return "redirect:/exacctTwos/" + exacctTwo.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, ExacctTwo exacctTwo) {
		map.put("exacctOnes", exacctOneService.findAll());
	}
	
	public void setAssociate(ExacctTwo exacctTwo
		, Integer exacctOneId
	) {
		if (exacctOneId != null) {
			ExacctOne exacctOne = exacctOneService.findById(exacctOneId);
			exacctTwo.setExacctOne(exacctOne);
		}
	}
	

}