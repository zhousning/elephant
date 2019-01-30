package app.daos.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.daos.ExpenseDao;
import app.models.Expense;

@Repository
public class ExpenseDaoImpl extends BaseDaoImpl<Expense> implements ExpenseDao{

	@Override
	public Integer[] findExplainIdById(Integer id) {
		String sql = "select explain_id, department_id from expenses where id = " + id.toString();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery(sql);
		Object[] objs = (Object[]) query.uniqueResult();
		Integer[] ids = {Integer.parseInt(objs[0].toString()), Integer.parseInt(objs[1].toString())};
		return ids;
	}
	
	@Override
	public List<Expense> selectByConditions(Integer departmentId, String start, String end) {
		String sql = "from Expense " + " where department_id = " + departmentId.toString();

		if (start != "") {
			sql +=  " and date >= '" + start + "'";
		}
		
		if (end != "") {
			sql += " and date <= '" + end + "'";
		}
		
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery(sql);
		return query.list();
	}

	@Override
	public List<Expense> findAllByDepId(Integer departmentId) {
		String sql = "from Expense " + " where department_id = " + departmentId.toString();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery(sql);
		return query.list();
	}

}