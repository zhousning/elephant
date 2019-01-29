package app.daos;

import java.util.List;

import app.models.Expense;

public interface ExpenseDao extends BaseDao<Expense> {

	public Integer[] findExplainIdById(Integer id);
	
	public List<Expense> selectByConditions(Integer departmentId, String start, String end);

}