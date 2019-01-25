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

import app.models.ExplainTitle;
import app.models.Explain;
import app.services.ExplainTitleService;
import app.services.ExplainService;


@Controller
@RequestMapping("/explainTitles")
public class ExplainTitleController extends BaseController {

	@Autowired
	ExplainTitleService explainTitleService;
	@Autowired
	ExplainService explainService;
	
	
	@ModelAttribute
	public void getexplainTitle(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("explainTitle", explainTitleService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("explainTitles", explainTitleService.findAll());
		return "explainTitles/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("explainTitle", new ExplainTitle());
		return "explainTitles/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		ExplainTitle explainTitle = explainTitleService.findById(id);
		prepareData(map, explainTitle);
		map.put("explainTitle", explainTitle);
		return "explainTitles/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("explainTitle", explainTitleService.findById(id));
		return "explainTitles/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		explainTitleService.deleteById(id);
		return "redirect:/explainTitles/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid ExplainTitle explainTitle, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/explainTitles/new";
		}
		
		setAssociate(explainTitle
		);
		
			
		explainTitleService.save(explainTitle);
		return "redirect:/explainTitles/" + explainTitle.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid ExplainTitle explainTitle, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, explainTitle);
			return "/explainTitles/edit";
		}
		
		setAssociate(explainTitle
		);
		
		
		explainTitleService.update(explainTitle);
		return "redirect:/explainTitles/" + explainTitle.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, ExplainTitle explainTitle) {
	}
	
	public void setAssociate(ExplainTitle explainTitle
	) {
	}
	

}