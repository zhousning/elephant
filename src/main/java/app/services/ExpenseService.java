package app.services;

import java.util.List;

import app.models.Expense;

public interface ExpenseService extends BaseService<Expense> {

	public Integer[] findExplainIdDeptIdById(Integer id);
	
	public List<Expense> selectByConditions(Integer departmentId, String start, String end);

	public List<Expense> findAllByDepId(Integer id);

}