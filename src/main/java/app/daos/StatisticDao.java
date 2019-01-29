package app.daos;

import java.util.List;
import java.util.Map;

import app.models.Expense;
import app.models.Statistic;

public interface StatisticDao extends BaseDao<Statistic> {
	public List<Statistic> depSumCostPerMonth(Integer departmentId, String start, String end);

	public List<Statistic> depExacctCostPerMonth(Integer departmentId, Integer exacctId, String start, String end);

	public List<Statistic> allDepExacctCostPerMonth(Integer exacctId, String start, String end);

	public List<Statistic> allCostPerMonth(String start, String end);

	public List<Statistic> allCostPerDepByMonth(String start, String end);

	public List<Statistic> allCostPerDepByMonthAndExacct(Integer exacctId, String start, String end);
	
	public String depStaffCount(Integer depId);
	
	public String depCostSum(Integer depId, String start, String end);
	
	public List<Statistic> depExacctCostSum(Integer depId, String start, String end);
	
	public Map<String, Object> allCostByStaff(String start, String end);

}