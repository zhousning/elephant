package app.services.Impl;

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

}