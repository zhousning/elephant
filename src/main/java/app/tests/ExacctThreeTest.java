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

import app.services.ExacctOneService;
import app.services.ExacctThreeService;
import app.services.ExacctTwoService;
import app.works.InitExacct;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ExacctThreeTest {
	@Autowired
	ExacctOneService exacctOneService;
	@Autowired
	ExacctTwoService exacctTwoService;
	@Autowired
	ExacctThreeService exacctThreeService;
	
	@Test
	public void Tool() {
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