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

import app.models.Explain;
import app.models.Expense;
import app.models.ExplainTitle;
import app.services.ExplainService;
import app.services.ExpenseService;
import app.services.ExplainTitleService;


@Controller
@RequestMapping("/explains")
public class ExplainController extends BaseController {

	@Autowired
	ExplainService explainService;
	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExplainTitleService explainTitleService;
	
	
	@ModelAttribute
	public void getexplain(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("explain", explainService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("explains", explainService.findAll());
		return "explains/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("explain", new Explain());
		return "explains/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Explain explain = explainService.findById(id);
		prepareData(map, explain);
		map.put("explain", explain);
		return "explains/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("explain", explainService.findById(id));
		return "explains/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		explainService.deleteById(id);
		return "redirect:/explains/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Explain explain, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/explains/new";
		}
		
		setAssociate(explain
		);
		
			
		explainService.save(explain);
		return "redirect:/explains/" + explain.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Explain explain, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, explain);
			return "/explains/edit";
		}
		
		setAssociate(explain
		);
		
		
		explainService.update(explain);
		return "redirect:/explains/" + explain.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, Explain explain) {
	}
	
	public void setAssociate(Explain explain
	) {
	}
	

}