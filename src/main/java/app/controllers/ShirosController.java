package app.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.models.Department;
import app.models.Role;
import app.models.User;
import app.services.DepartmentService;
import app.services.RoleService;
import app.services.UserService;
import app.works.MailUtil;

@Controller 
@RequestMapping("/users")
public class ShirosController extends BaseController {
	
	@Autowired
	UserService userService;
	@Autowired
	DepartmentService departmentService;
	
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(userService.findById(id).toString());
			map.put("user", userService.findById(id));
		}
	}
	
	@RequestMapping("/sign_up")
	public String registrationsNew(Map<String, Object> map) {
		map.put("user", new User());
		map.put("departments", departmentService.findAll());
		return "users/registrations/new";
	}
	
	@RequestMapping(value="/sign_up", method=RequestMethod.POST)
	public String registrationsCreate(@Valid User user, @RequestParam(value="departmentId") Integer departmentId, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			//若验证出错, 则转向定制的页面
			return "/users/registrations/new";
		}
		//使用邮箱注册，如果改成其他需要修改以下两行内容
		String principal = user.getEmail();
		User selectUser = userService.getUserByEmail(principal);
		if (selectUser == null) {
			setPassword(user);
			initRole(user);
			Department department = departmentService.findById(departmentId);
			user.setDepartment(department);
			userService.save(user);
			return "redirect:/users/sign_in";
		} else {
			//throw new ConstraintViolationException(constraintViolations)
			return "/users/registrations/new";
		}	
	}
	
	@RequestMapping("/sign_in")
	public String sessionsNew(Map<String, Object> map) {
		map.put("user", new User());
		return "/users/sessions/new";
	}
	
	@RequestMapping(value="/sign_in", method=RequestMethod.POST)
	public String sessionsCreate(User user, Map<String, Object> map){
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
            // rememberme
            //token.setRememberMe(true);
            try {
            	// 执行登录. 
                currentUser.login(token);
            } 
            // 所有认证时异常的父类. 
            catch (AuthenticationException ae) {
            	System.out.println("登录失败: " + ae.getMessage());
        		return "/users/sessions/new";
            }
        }
		return "redirect:/home";
	}
	
	
	@RequestMapping(value = "/passwords/new")
	public String passwordNew(Map<String, Object> map) {
		User user = new User();
		map.put("user", user);
		return "users/passwords/new";
	}
	
	@RequestMapping(value = "/passwords/{email}/code")
	@ResponseBody
	public Map<String, String> sendEmail(@PathVariable("email") String email, HttpSession session) throws MessagingException {
		String code = String.valueOf((int)((Math.random()*9+1)*100000));
		session.setAttribute("code", code);
		MailUtil.send_mail(email, code);
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String updatePassword(User user, @RequestParam(value="verification", required=false) String code, Errors result, HttpSession session) {	
		String sessionCode = (String) session.getAttribute("code");
		if (code!=null && sessionCode.equals(code)) {
			String principal = user.getEmail();
			
			User myUser = userService.getUserByEmail(principal);
			setPassword(myUser);
			userService.update(myUser);
			return "redirect:/users/sign_in";
		} else {
			return "redirect:/users/passwords/new";
		}
	}
}
