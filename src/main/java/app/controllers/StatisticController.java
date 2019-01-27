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

import app.models.Statistic;
import app.services.StatisticService;


@Controller
@RequestMapping("/statistics")
public class StatisticController extends BaseController {

	@Autowired
	StatisticService statisticService;
	
	
	@ModelAttribute
	public void getstatistic(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("statistic", statisticService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("statistics", statisticService.findAll());
		return "statistics/index";
	}
	
	public void totalExpenseByDepAndDate(Map<String, Object> map
		, @RequestParam(value="departmentId", required=false) Integer departmentId
		, @RequestParam(value = "start", required = false) String start
		, @RequestParam(value = "end", required = false) String end) {
		
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
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("statistic", statisticService.findById(id));
		return "statistics/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		statisticService.deleteById(id);
		return "redirect:/statistics/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Statistic statistic, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/statistics/new";
		}
		
		
			
		statisticService.save(statistic);
		return "redirect:/statistics/" + statistic.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Statistic statistic, Errors result, Map<String, Object> map
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
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