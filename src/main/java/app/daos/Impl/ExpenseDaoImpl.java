package app.daos.Impl;

import org.springframework.stereotype.Repository;

import app.daos.ExpenseDao;
import app.models.Expense;

@Repository
public class ExpenseDaoImpl extends BaseDaoImpl<Expense> implements ExpenseDao{

}