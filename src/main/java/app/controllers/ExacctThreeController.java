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

import app.models.ExacctThree;
import app.models.ExacctTwo;
import app.models.Expense;
import app.services.ExacctThreeService;
import app.services.ExacctTwoService;
import app.services.ExpenseService;


@Controller
@RequestMapping("/exacctThrees")
public class ExacctThreeController extends BaseController {

	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	ExacctTwoService exacctTwoService;
	@Autowired
	ExpenseService expenseService;
	
	
	@ModelAttribute
	public void getexacctThree(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("exacctThree", exacctThreeService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("exacctThrees", exacctThreeService.findAll());
		return "exacctThrees/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("exacctThree", new ExacctThree());
		return "exacctThrees/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		ExacctThree exacctThree = exacctThreeService.findById(id);
		prepareData(map, exacctThree);
		map.put("exacctThree", exacctThree);
		return "exacctThrees/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("exacctThree", exacctThreeService.findById(id));
		return "exacctThrees/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		exacctThreeService.deleteById(id);
		return "redirect:/exacctThrees/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid ExacctThree exacctThree, Errors result, Map<String, Object> map
		, @RequestParam(value="exacctTwo.id", required=false) Integer exacctTwoId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/exacctThrees/new";
		}
		
		setAssociate(exacctThree
		, exacctTwoId
		);
		
			
		exacctThreeService.save(exacctThree);
		return "redirect:/exacctThrees/" + exacctThree.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid ExacctThree exacctThree, Errors result, Map<String, Object> map
		, @RequestParam(value="exacctTwo.id", required=false) Integer exacctTwoId
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, exacctThree);
			return "/exacctThrees/edit";
		}
		
		setAssociate(exacctThree
		, exacctTwoId
		);
		
		
		exacctThreeService.update(exacctThree);
		return "redirect:/exacctThrees/" + exacctThree.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, ExacctThree exacctThree) {
		map.put("exacctTwos", exacctTwoService.findAll());
	}
	
	public void setAssociate(ExacctThree exacctThree
		, Integer exacctTwoId
	) {
		if (exacctTwoId != null) {
			ExacctTwo exacctTwo = exacctTwoService.findById(exacctTwoId);
			exacctThree.setExacctTwo(exacctTwo);
		}
	}
	

}