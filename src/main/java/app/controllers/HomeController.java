package app.controllers;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;

import app.models.User;
import app.services.DepartmentService;
import app.services.ExacctThreeService;
import app.services.StatisticService;
import app.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	StatisticService statisticService;

	@RequestMapping("")
	public String index(Map<String, Object> map) {
		if (!adminRole()) {
			Subject currentUser = SecurityUtils.getSubject();
			String principal = currentUser.getPrincipal().toString();
			User user = userService.getUserByEmail(principal);
			map.put("user", user);
		}
		map.put("departments", departmentService.findAll());
		map.put("exaccts", exacctThreeService.findAll());
		return "home/index";
	}
	
	
	/*@RequestMapping("/{id}/export_doc")
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id, HttpSession session) throws Exception{
    	String file = exportDoc(id, session);
        byte [] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/download/" + file);
        body = new byte[in.available()];
        in.read(body);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+file);
        
        HttpStatus statusCode = HttpStatus.OK;
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }*/
}
