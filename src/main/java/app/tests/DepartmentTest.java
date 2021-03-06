package app.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.services.CenterService;
import app.services.DepartmentService;
import app.works.InitDepartment;
import app.works.InitUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DepartmentTest {
	@Autowired
	CenterService centerService;
	@Autowired
	DepartmentService departmentService;
	@Test
	public void Tool() {
		String filePath = "F:\\Project\\elephant\\src\\main\\webapp\\static\\xls\\department.xlsx";
		InitDepartment.init(filePath, centerService, departmentService);
	}
		
	@Test
	public void index() {
		
	}
	
	@Test
	public void create() {
		
	}
	
	@Test
	public void edit() {
		
	}
	
	@Test
	public void delete() {
		
	}
}