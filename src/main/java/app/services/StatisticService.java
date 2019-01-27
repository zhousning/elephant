package app.services;

import java.util.List;

import app.models.Expense;
import app.models.Statistic;

public interface StatisticService extends BaseService<Statistic> {
	public List<Statistic> depSumCostPerMonth(Integer departmentId, String start, String end);

	public List<Statistic> depExacctCostPerMonth(Integer departmentId, Integer exacctId, String start, String end);

	public List<Statistic> allDepExacctCostPerMonth(Integer exacctId, String start, String end);

	public List<Statistic> allCostPerMonth(String start, String end);

	public List<Statistic> allCostPerDepByMonth(String start, String end);

	public List<Statistic> allCostPerDepByMonthAndExacct(Integer exacctId, String start, String end);
}