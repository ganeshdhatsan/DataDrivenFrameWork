package DataDrivenFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataFromWorkbook {

	private String[][] getDataFromWorkBook() throws IOException {
		DataFormatter format = new DataFormatter();
		File file = new File(
				"C:\\Users\\Lenovo\\eclipse-workspace\\Ganesh\\DataDrivenFramework_Ganesh\\src\\test\\resources\\TestData\\Tricentis_TestData.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Tosca");
//		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//		System.out.println("physicalNumberOfRows --> " + physicalNumberOfRows);
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum --> " + lastRowNum);
		XSSFRow row = sheet.getRow(0);
//		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
//		System.out.println("physicalNumberOfCells --> " + physicalNumberOfCells);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum --> " + lastCellNum);
//		String data = null;
		String[][] result = new String[lastRowNum][lastCellNum];
		System.out.println("size of array " + result.length);
		for (int i = 1; i < lastRowNum + 1; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {
//				String stringCellValue = cell.getStringCellValue();
						String data = format.formatCellValue(cell);
						result[i - 1][j] = data;
						System.out.println("result --> " + (i - 1) + ":" + j + " " + data);
					}

				}

			}

		}
		book.close();
		stream.close();
		return result;
	}
	
	private void getDataFromWorkBook1() throws IOException {
		DataFormatter format = new DataFormatter();
		File file = new File(
				"C:\\Users\\Lenovo\\eclipse-workspace\\Ganesh\\DataDrivenFramework_Ganesh\\src\\test\\resources\\TestData\\Tricentis_TestData.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Tosca");
//		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//		System.out.println("physicalNumberOfRows --> " + physicalNumberOfRows);
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum --> " + lastRowNum);
		XSSFRow row = sheet.getRow(0);
//		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
//		System.out.println("physicalNumberOfCells --> " + physicalNumberOfCells);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum --> " + lastCellNum);
//		String data = null;
		String[][] result = new String[lastRowNum][lastCellNum];
		System.out.println("size of array " + result.length);
		for (int i = 1; i < lastRowNum + 1; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {
//				String stringCellValue = cell.getStringCellValue();
						String data = format.formatCellValue(cell);
						result[i - 1][j] = data;
						System.out.println("result --> " + (i - 1) + ":" + j + " " + data);
					}

				}

			}

		}
		book.close();
		stream.close();
		
	}


	public static void main(String[] args) throws IOException {
		DataFromWorkbook d = new DataFromWorkbook();
		d.getDataFromWorkBook();
	}
}
