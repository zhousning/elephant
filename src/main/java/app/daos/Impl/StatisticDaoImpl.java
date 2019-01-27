package app.daos.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.daos.DepartmentDao;
import app.daos.StatisticDao;
import app.models.Department;
import app.models.Expense;
import app.models.Statistic;

@Repository
public class StatisticDaoImpl extends BaseDaoImpl<Statistic> implements StatisticDao{
	@Autowired
	DepartmentDao departmentDao;
	
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
		System.out.println(sql);
		
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
		System.out.println(sql);
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
}