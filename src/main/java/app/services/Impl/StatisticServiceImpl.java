package app.services.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.StatisticDao;
import app.models.Expense;
import app.models.Statistic;
import app.services.StatisticService;

@Service
public class StatisticServiceImpl extends BaseServiceImpl<Statistic> implements StatisticService {
	@Autowired
	StatisticDao statisticDao;
	@Override
	public List<Statistic> depSumCostPerMonth(Integer departmentId, String start, String end) {
		return statisticDao.depSumCostPerMonth(departmentId, start, end);
	}
	@Override
	public List<Statistic> depExacctCostPerMonth(Integer departmentId, Integer exacctId, String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.depExacctCostPerMonth(departmentId, exacctId, start, end);
	}
	@Override
	public List<Statistic> allDepExacctCostPerMonth(Integer exacctId, String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.allDepExacctCostPerMonth(exacctId, start, end);
	}
	@Override
	public List<Statistic> allCostPerMonth(String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.allCostPerMonth(start, end);
	}
	@Override
	public List<Statistic> allCostPerDepByMonth(String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.allCostPerDepByMonth(start, end);
	}
	@Override
	public List<Statistic> allCostPerDepByMonthAndExacct(Integer exacctId, String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.allCostPerDepByMonthAndExacct(exacctId, start, end);
	}
	@Override
	public String depStaffCount(Integer depId) {
		// TODO Auto-generated method stub
		return statisticDao.depStaffCount(depId);
	}
	@Override
	public String depCostSum(Integer depId, String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.depCostSum(depId, start, end);
	}
	@Override
	public List<Statistic> depExacctCostSum(Integer depId, String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.depExacctCostSum(depId, start, end);
	}
	@Override
	public Map<String, Object> allCostByStaff(String start, String end) {
		// TODO Auto-generated method stub
		return statisticDao.allCostByStaff(start, end);
	}
	
}