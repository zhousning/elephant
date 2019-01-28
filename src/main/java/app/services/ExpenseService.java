package app.services;

import app.models.Expense;

public interface ExpenseService extends BaseService<Expense> {

	public Integer[] findExplainIdDeptIdById(Integer id);

}