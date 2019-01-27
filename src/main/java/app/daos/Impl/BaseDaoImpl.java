package app.daos.Impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import app.daos.BaseDao;
import app.models.Department;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	HibernateTemplate hibernateTemplate;

	private Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void batchSave(List<T> entities) {
		hibernateTemplate.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				if (CollectionUtils.isNotEmpty(entities)) {
					try {
						int i = 0;
						for (T t : entities) {
							session.save(t);
							i++;
							if (i % 100 == 0) {
								session.flush();
								session.clear();
							}
						}
					} catch (Exception e) {
						System.out.println("批量插入出现异常：" + e);
					} finally {
						if (session != null)
							session.close();
					}
				}
				return null;
			}
		});
	}

	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

	public void deleteById(Integer id) {
		T entity = hibernateTemplate.get(clazz, id);
		hibernateTemplate.delete(entity);
	}

	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	public T findById(Integer id) {
		return hibernateTemplate.get(clazz, id);
	}

	public T findByName(String name) {
		String hql = "from " + clazz.getSimpleName() + " u where u.name = :name";
		List<T> objs = (List<T>) hibernateTemplate.findByNamedParam(hql, "name", name);
		T obj = null;
		if (objs.size() != 0) {
			obj = objs.get(0);
		}
		return obj;
	}

	public List<T> findAll() {
		return (List<T>) hibernateTemplate.find("from " + clazz.getSimpleName());
	}

	public List<T> findByIds(Integer[] ids) {
		String sql = "from " + clazz.getSimpleName() + " where id in :ids";
		return (List<T>) hibernateTemplate.findByNamedParam(sql, "ids", ids);
	}
}
