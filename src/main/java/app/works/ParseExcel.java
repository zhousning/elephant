package app.works;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ParseExcel {
	public static List<Map<String, String>> parseExcelContent(MultipartFile file) {
		Workbook wb = null;
		wb = readExcel(file);
		List<Map<String, String>> list = processExcel(wb);
		// 遍历解析出来的list
		/*
		 * for (Map<String,String> map : list) { for (Entry<String,String> entry :
		 * map.entrySet()) { System.out.print(entry.getKey()+":"+entry.getValue()+",");
		 * } System.out.println(); }
		 */
		return list;
	}

	public static List<Map<String, String>> parseExcelContentFromPath(String filePath) {
		Workbook wb = null;
		wb = readExcelFromPath(filePath);
		List<Map<String, String>> list = processExcel(wb);
		return list;
	}

	// result [{"title0":title0, "title1":title1},{"title0":title0,
	// "title1":title1}]
	public static List<Map<String, String>> processExcel(Workbook wb) {
		Sheet sheet = null;
		Row row = null;
		List<Map<String, String>> list = null;
		String cellData = null;
		if (wb != null) {
			list = new ArrayList<Map<String, String>>();
			sheet = wb.getSheetAt(0);
			int rownum = sheet.getPhysicalNumberOfRows();
			Row row0 = sheet.getRow(0);
			int colnum = row0.getPhysicalNumberOfCells();
			for (int i = 1; i < rownum; i++) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				row = sheet.getRow(i);
				if (row != null) {
					for (int j = 0; j < colnum; j++) {
						cellData = (String) getCellFormatValue(row.getCell(j));
						String key = (String) getCellFormatValue(row0.getCell(j));
						map.put(key.trim(), cellData);
					}
				} else {
					break;
				}
				list.add(map);
			}
		}
		return list;
	}

	public static Workbook readExcel(MultipartFile file) {
		Workbook wb = null;
		if (file == null) {
			return null;
		}
		String fileName = file.getOriginalFilename();
		String extString = fileName.substring(fileName.lastIndexOf("."));
		InputStream is = null;
		try {
			is = file.getInputStream();
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static Workbook readExcelFromPath(String filePath) {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static Object getCellFormatValue(Cell cell) {
		Object cellValue = null;
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				// 判断cell是否为日期格式
				if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					cellValue = cell.getDateCellValue();
				} else {
					// 数字
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			default:
				cellValue = "";
			}
		} else {
			cellValue = "";
		}
		return cellValue;
	}
}
