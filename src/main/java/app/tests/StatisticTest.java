package app.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.models.Expense;
import app.models.Statistic;
import app.services.StatisticService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class StatisticTest {
	@Autowired
	StatisticService statisticService;
	Integer departmentId = 709;
	String start = "2017-01-01";
	String end = "2019-02-03";
	Integer exacctId = 617;
	@Test
	public void Tool() {
		
	}
	
	@Test
	public void depSumCostPerMonth(){
		List<Statistic> statistics = statisticService.depSumCostPerMonth(departmentId, start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum().toString() + "  " + statistic.getLabel());
		}
	}
	@Test
	public void depExacctCostPerMonth(){
		List<Statistic> statistics = statisticService.depExacctCostPerMonth(departmentId, exacctId, start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum() + "  " + statistic.getLabel());
		}
	}
	@Test
	public void allDepExacctCostPerMonth(){
		List<Statistic> statistics = statisticService.allDepExacctCostPerMonth(exacctId, start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum() + "  " + statistic.getLabel());
		}
	}
	@Test
	public void allCostPerMonth(){ 
		List<Statistic> statistics = statisticService.allCostPerMonth(start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum() + "  " + statistic.getLabel());
		}
	}
	@Test
	public void allCostPerDepByMonth(){
		List<Statistic> statistics = statisticService.allCostPerDepByMonth(start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum() + "  " + statistic.getLabel());
		}
	}
	@Test
	public void allCostPerDepByMonthAndExacct(){
		List<Statistic> statistics = statisticService.allCostPerDepByMonthAndExacct(exacctId, start, end);
		for (Statistic statistic  : statistics) {
			System.out.println(statistic.getSum() + "  " + statistic.getLabel());
		}
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