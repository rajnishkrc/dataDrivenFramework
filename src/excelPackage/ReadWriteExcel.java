package excelPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcel {

	public List<String> userName;
	public List<String> password;
	
	// hirearchy
	// Workbook
	// Sheet
	// Row
	// Column
	// Cell

	public void readExcel() {
		File src = new File("resources/test.xlsx");
		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sh1 = wb.getSheetAt(0);

			userName = new ArrayList<>();

			password = new ArrayList<>();

			int totalRows = sh1.getLastRowNum();
			for (int k = 1; k < totalRows; k++) {
				userName.add(sh1.getRow(k).getCell(0).getStringCellValue());
				password.add(sh1.getRow(k).getCell(1).getStringCellValue());

			}
			wb.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
