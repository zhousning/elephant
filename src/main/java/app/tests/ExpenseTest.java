package app.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.models.Department;
import app.models.ExacctThree;
import app.models.Expense;
import app.models.Explain;
import app.services.DepartmentService;
import app.services.ExacctThreeService;
import app.services.ExpenseService;
import app.services.ExplainService;
import app.services.UserService;
import app.works.ParseExcel;

import org.apache.poi.ss.usermodel.DateUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ExpenseTest {
	@Autowired
	ExpenseService expenseService;
	@Autowired
	ExacctThreeService exacctThreeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserService userService;
	@Autowired
	ExplainService explainService;
	
	@Test
	public void Tool() {
		uploadExcel();
	}
	
	public String uploadExcel() {
		String filePath = "F:\\Project\\elephant\\src\\main\\webapp\\static\\xls\\expense.xlsx";
		List<Map<String, String>> list = ParseExcel.parseExcelContentFromPath(filePath);
		String exacctThreeName = "快递";
		
		Map<String, Explain> explainMap = getExplainMap();
		Map<String, Department> departmentMap = getDepartmentMap();
		Map<String, ExacctThree> exacctThreeMap = getExacctThreeMap();
		
		Calendar calendar = new GregorianCalendar(1900,0,-1);  
		Date d = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
		for (Map<String, String> map : list) {
			Date date;
			try {
				String month = map.get("月份");
				month = month.substring(0, month.lastIndexOf("."));
				date = DateUtils.addDays(d,Integer.valueOf(month));
			} catch (Exception e) {
				date = new Date();
			}
			String staffid = map.get("工号");
			staffid = staffid == null ? "" : staffid.substring(0, staffid.lastIndexOf("."));
			String staffname = map.get("姓名");
			staffname = staffname == null ? "" : staffname;
			String departmentName = map.get("部门");
			
			float sum = 0;
			try {
				sum = Float.parseFloat(map.get(exacctThreeName));
			} catch (Exception e) {
		
			}

			map.remove("月份");
			map.remove("工号");
			map.remove("姓名");
			map.remove("部门");
			map.remove("中心/工厂");
			map.remove(exacctThreeName);

			String info = "";
			String explainTitle = "";
			for (Entry<String, String> entry : map.entrySet()) {
				explainTitle += entry.getKey() + "SplitLine";
				String val = entry.getValue();
				val = val == null ? "" : entry.getValue();
				info += val + "SplitLine";
			}
			
			Explain explain = explainMap.get(info);
			if (explain == null) {
				explain = new Explain(explainTitle);
				explainService.save(explain);
				explainMap.put(explain.getName(), explain);
			}
			
			Department department = departmentMap.get(departmentName);
			ExacctThree exacctThree = exacctThreeMap.get(exacctThreeName);
			if (department != null && exacctThree != null) {
				Expense expense = new Expense(date, staffid, staffname, sum, info);
				expense.setDepartment(department);
				expense.setExacctThree(exacctThree);
				expense.setExplain(explain);
				expenseService.save(expense);
			}
		}
		return"expenses/index";

	}
	
	public Map<String, Explain> getExplainMap() {
		List<Explain> explains = explainService.findAll();
		Map<String, Explain> explainMap = new HashMap<String, Explain>();
		for (Explain explain : explains) {
			explainMap.put(explain.getName(), explain);
		}
		return explainMap;
	}
	
	public Map<String, Department> getDepartmentMap() {
		List<Department> departments = departmentService.findAll();
		Map<String, Department> departmentMap = new HashMap<String, Department>();
		for (Department department : departments) {
			departmentMap.put(department.getName(), department);
		}
		return departmentMap;
	}
	
	public Map<String, ExacctThree> getExacctThreeMap() {
		List<ExacctThree> ExacctThrees = exacctThreeService.findAll();
		Map<String, ExacctThree> ExacctThreeMap = new HashMap<String, ExacctThree>();
		for (ExacctThree ExacctThree : ExacctThrees) {
			ExacctThreeMap.put(ExacctThree.getName(), ExacctThree);
		}
		return ExacctThreeMap;
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