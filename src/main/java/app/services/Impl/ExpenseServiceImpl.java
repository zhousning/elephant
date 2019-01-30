package app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.ExpenseDao;
import app.models.Expense;
import app.services.ExpenseService;

@Service
public class ExpenseServiceImpl extends BaseServiceImpl<Expense> implements ExpenseService {
	@Autowired
	ExpenseDao expenseDao;
	@Override
	public Integer[] findExplainIdDeptIdById(Integer id) {
		return expenseDao.findExplainIdById(id);
	}
	@Override
	public List<Expense> selectByConditions(Integer departmentId, String start, String end) {
		return expenseDao.selectByConditions(departmentId, start, end);
	}
	@Override
	public List<Expense> findAllByDepId(Integer id) {
		// TODO Auto-generated method stub
		return expenseDao.findAllByDepId(id);
	}

}