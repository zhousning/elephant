package app.works;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpRequest;

import app.models.ExacctOne;
import app.models.ExacctThree;
import app.models.ExacctTwo;
import app.services.ExacctOneService;
import app.services.ExacctThreeService;
import app.services.ExacctTwoService;

public class InitExacct {
	public static void init(String filePath, ExacctOneService exacctOneService, ExacctTwoService exacctTwoService, ExacctThreeService exacctThreeService) {
		List<Map<String, String>> list = ParseExcel.parseExcelContentFromPath(filePath);
		
		for(Map<String, String> map : list) {
			String exacctOneName = map.get("exacctOne").trim();
			String exacctTwoName = map.get("exacctTwo").trim();
			String exacctThreeName = map.get("exacctThree").trim();
			
			ExacctOne exacctOne = null;
			ExacctTwo exacctTwo = null;
			ExacctThree exacctThree = null;
			
			exacctOne = exacctOneService.findByName(exacctOneName);
			if (exacctOne == null) {
				exacctOne = new ExacctOne(exacctOneName);
				exacctOneService.save(exacctOne);
			}
			
			exacctTwo = exacctTwoService.findByName(exacctTwoName);
			if (exacctTwo == null) {
				exacctTwo = new ExacctTwo(exacctTwoName);
				exacctTwo.setExacctOne(exacctOne);
				exacctTwoService.save(exacctTwo);
			}
		
			exacctThree = exacctThreeService.findByName(exacctThreeName);
			if (exacctThree == null) {
				exacctThree = new ExacctThree(exacctThreeName);
				exacctThree.setExacctTwo(exacctTwo);
				exacctThreeService.save(exacctThree);
			}
		}
	}
}
