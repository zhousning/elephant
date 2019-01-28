package app.daos;

import app.models.Expense;

public interface ExpenseDao extends BaseDao<Expense> {

	public Integer[] findExplainIdById(Integer id);

}