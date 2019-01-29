package app.daos.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.daos.DepartmentDao;
import app.daos.StatisticDao;
import app.models.Center;
import app.models.Department;
import app.models.Expense;
import app.models.Statistic;
import app.services.CenterService;

@Repository
public class StatisticDaoImpl extends BaseDaoImpl<Statistic> implements StatisticDao{
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	CenterService centerService;
	
	//部门自己每个月总费用
	@Override
	public List<Statistic> depSumCostPerMonth(Integer departmentId, String start, String end) {
		String sql = "select sum(sum) sum, DATE_FORMAT(date, '%Y-%m') date " + 
				"from expenses " + 
				"where date between '" + start + "' and '" + end + 
				"' and department_id = " + departmentId.toString() + 
				" group by DATE_FORMAT(date, '%Y-%m')  order by  DATE_FORMAT(date, '%Y-%m') ";
		
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			Statistic statistic = new Statistic(object[1].toString(), object[0].toString());
			statistics.add(statistic);
		}
		return statistics;
	}
	
	//部门自己每个月某项费用
	public List<Statistic> depExacctCostPerMonth(Integer departmentId, Integer exacctId, String start, String end) {
		String sql = "select sum(sum) sum, DATE_FORMAT(date, '%Y-%m') date " + 
				"from expenses " + 
				"where date between '" + start + "' and '" + end + 
				"' and department_id = " + departmentId.toString() + 
				" and exacctThree_id = " + exacctId.toString() + 
				" group by DATE_FORMAT(date, '%Y-%m')  order by  DATE_FORMAT(date, '%Y-%m') ";
		
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			Statistic statistic = new Statistic(object[1].toString(), object[0].toString());
			statistics.add(statistic);
		}
		return statistics;
	}
	
	//每个月某项费用所有部门的花费
	public List<Statistic> allDepExacctCostPerMonth(Integer exacctId, String start, String end) {
		String sql = "select sum(a.sum), a.date from (select sum(sum) sum, DATE_FORMAT(date, '%Y-%m') date " + 
				"from expenses " + 
				"where date between '" + start + "' and '" + end + 
				"' and exacctThree_id = " + exacctId.toString() + 
				" group by DATE_FORMAT(date, '%Y-%m'), department_id ) a group by a.date order by a.date";	
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			Statistic statistic = new Statistic(object[1].toString(), object[0].toString());
			statistics.add(statistic);
		}
		return statistics;
	}
	
	//每个月所有花费
		public List<Statistic> allCostPerMonth(String start, String end) {
			String sql = "select sum(sum) sum, DATE_FORMAT(date, '%Y-%m') date " + 
					"from expenses " + 
					"where date between '" + start + "' and '" + end + 
					"' group by DATE_FORMAT(date, '%Y-%m') order by  DATE_FORMAT(date, '%Y-%m') ";
			
			SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
			Session currentSession = sessionFactory.getCurrentSession();
			Query query = currentSession.createSQLQuery(sql);
			List<Object[]> lists = query.list();	
			
			List<Statistic> statistics = new ArrayList<Statistic>();
			for (Object[] object : lists) {
				Statistic statistic = new Statistic(object[1].toString(), object[0].toString());
				statistics.add(statistic);
			}
			return statistics;
		}
	
	//某个月每个部门总的花费 横柱图
	public List<Statistic> allCostPerDepByMonth(String start, String end) {
		String sql = "select sum(sum) sum, department_id " + 
				"from expenses " + 
				"where date between '" + start + "' and '" + end + 
				"' group by department_id";
		System.out.println(sql);
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			Department department = departmentDao.findById(Integer.parseInt(object[1].toString()));
			Statistic statistic = new Statistic(department.getName(), object[0].toString());
			statistics.add(statistic);
		}
		return statistics;
	}
	
	//某个月每个部门某项费用总花费
	public List<Statistic> allCostPerDepByMonthAndExacct(Integer exacctId, String start, String end) {
		String sql = "select sum(sum) sum, department_id " + 
				"from expenses " + 
				"where date between '" + start + "' and '" + end + 
				"' and exacctThree_id = " + exacctId.toString() + 
				" group by department_id";
		
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		List<Object[]> lists = query.list();
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			Department department = departmentDao.findById(Integer.parseInt(object[1].toString()));
			Statistic statistic = new Statistic(department.getName(), object[0].toString());
			statistics.add(statistic);
		}
		return statistics;
	}
	
	public String depStaffCount(Integer depId) {
		String sql = "SELECT COUNT(DISTINCT staffid) FROM expenses e WHERE e.department_id = " + depId.toString() + " AND LENGTH(trim(staffid))>1";
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		Object object = query.uniqueResult();
		String result = object != null ? object.toString() : "";
		return result;
	}
	
	public String depCostSum(Integer depId, String start, String end) {
		String sql = "SELECT SUM(sum) FROM expenses e WHERE e.department_id = " + depId.toString() +
				" AND date BETWEEN '" + start + "' AND '" + end + "' ";
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		Object object = query.uniqueResult();
		String result = object != null ? object.toString() : "";
		return result;
	}
	
	public List<Statistic> depExacctCostSum(Integer depId, String start, String end) {
		String sql = "SELECT exacctthrees.name, SUM(sum) FROM expenses e RIGHT JOIN exacctthrees ON e.exacctThree_id =  exacctthrees.id "
		+ " AND e.department_id = " + depId.toString()
		+ " AND date BETWEEN '" + start + "' AND '" + end + "' "
		+ " GROUP BY exacctthrees.id  ORDER BY exacctthrees.id ";
		System.out.println(sql);
		Query query = getSession().createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		List<Statistic> statistics = new ArrayList<Statistic>();
		for (Object[] object : lists) {
			String sum = object[1] != null ? object[1].toString() : "";
			Statistic statistic = new Statistic(object[0].toString(), sum);
			statistics.add(statistic);
		}
		return statistics;
	}
	
	public Map<String, Object> allCostByStaff(String start, String end) {
		String sql = "SELECT DATE_FORMAT(e.date, '%Y-%m'), e.staffid,  e.staffname, d.name dep, SUM(e.sum), t.name exacct from expenses e " +
				" INNER JOIN departments d ON e.department_id = d.id " + 
				" INNER JOIN exacctthrees t ON e.exacctThree_id = t.id " + 
				" AND  e.date BETWEEN '" + start +"' AND '" + end + "' " + 
				"  AND LENGTH(trim(e.staffid))>1 GROUP BY e.staffid, t.id ORDER BY e.date";
		Query query = getSession().createSQLQuery(sql);
		List<Object[]> lists = query.list();	
		
		Map<String, String> depCenterMap = depCenter();
		Map<String, Float> sumMap = new LinkedHashMap<String, Float>();
		
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		for (Object[] object : lists) {
			Float sum = 0f;
			Map<String, String> expense = null;
			if (result.get(object[1].toString()) == null) {
				expense = new LinkedHashMap<String, String>();
				expense.put("月份", object[0].toString());
				expense.put("姓名", object[2].toString());
				expense.put("中心/工厂", depCenterMap.get(object[3].toString()));
				expense.put("部门", object[3].toString());
			} else {
				expense = (Map<String, String>) result.get(object[1].toString());
				sum = sumMap.get(object[1].toString());
			}
			sum += Float.parseFloat(object[4].toString());
			expense.put(object[5].toString(), object[4].toString());
			sumMap.put(object[1].toString(), sum);
			result.put(object[1].toString(), expense);
		}
		
		for (Map.Entry<String, Float> entry : sumMap.entrySet()) {
			((Map<String, Object>) result.get(entry.getKey())).put("总计", entry.getValue().toString());;
		}

		return result;
	}
	
	public Map<String, String> depCenter() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Department> departments = departmentDao.findAll();
		for (Department department : departments) {
			map.put(department.getName(), department.getCenter().getName());
		}
		return map;
	}
	
	public Session getSession() {
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}
}