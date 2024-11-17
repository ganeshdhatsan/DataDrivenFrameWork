package DataDrivenFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataFromWorkbookPractice {

	private String[][] getDataFromWorkBook() throws IOException {
		DataFormatter form = new DataFormatter();
		File file = new File(
				"C:\\Users\\Lenovo\\eclipse-workspace\\Ganesh\\DataDrivenFramework_Ganesh\\src\\test\\resources\\TestData\\Tricentis_TestData.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("tosca1");
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		int lastRowNum = sheet.getLastRowNum();
		short lastCellNum = row.getLastCellNum();
		String[][] result = new String[lastRowNum][lastCellNum];
		for (int i = 1; i < lastRowNum + 1; i++) {
			row = sheet.getRow(i);

			if (row != null) {
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {

						String cellValue = form.formatCellValue(cell);
						result[i - 1][j] = cellValue;
//			System.out.println("result --> " + (i - 1) + ":" + j + " " + cellValue);

					}

				}

			}

		}
		FileOutputStream fileout = new FileOutputStream(file);
		book.write(fileout);
		book.close();
		stream.close();
		for (String[] strings : result) {
			System.out.println("OUTPUT -- > " + Arrays.toString(strings));

		}
		return result;
	}
	
	
	private String[][] loginTestData() throws IOException {
		DataFormatter format = new DataFormatter();
		File file = new File(
				System.getProperty("user.dir")+"\\Login Data.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Login Data");
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum --> " + lastRowNum);
		XSSFRow row = sheet.getRow(0);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum --> " + lastCellNum);
		
		String[][] result = new String[lastRowNum][lastCellNum];
		System.out.println("size of array " + result.length);
		
		for (int i = 1; i <= lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell != null) {
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
	public static void main(String[] args) throws IOException {
		DataFromWorkbookPractice d = new DataFromWorkbookPractice();
		d.loginTestData1();
//		d.writeExcel();
	}

	
	private String[][] loginTestData1() throws IOException {
		DataFormatter format = new DataFormatter();
		File file = new File(
				System.getProperty("user.dir")+"\\Login Data.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.getSheet("Login Data");
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum --> " + lastRowNum);
		XSSFRow row = sheet.getRow(0);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum --> " + lastCellNum);
		
		String[][] result = new String[lastRowNum][lastCellNum];
		System.out.println("size of array " + result.length);
		
		for (int i = 1; i <=lastRowNum; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
				
						String data = format.formatCellValue(cell);
						result[i - 1][j] = data;
						System.out.println("result --> " + (i - 1) + ":" + j + " " + data);
				}

			}
		}
		book.close();
		stream.close();
		return result;
		
		}
	
	@Test
	private void validateLoginWithValidCredential() throws IOException {
		String[][] loginTestData1 = loginTestData1();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(loginTestData1[0][0]);
		driver.findElement(By.name("pass")).sendKeys(loginTestData1[0][1]);
		driver.findElement(By.name("login")).click();
	

	}
	private void validateLoginWithInvalidUserName() throws IOException {
		String[][] loginTestData1 = loginTestData1();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(loginTestData1[1][0]);
		driver.findElement(By.name("pass")).sendKeys(loginTestData1[1][1]);
		driver.findElement(By.name("login")).click();
	

	}
	
	private void validateLoginWithInvalidPassword() throws IOException {
		String[][] loginTestData1 = loginTestData1();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(loginTestData1[2][0]);
		driver.findElement(By.name("pass")).sendKeys(loginTestData1[2][1]);
		driver.findElement(By.name("login")).click();
	

	}
	
	private void validateLoginWithoutValue() throws IOException {
		String[][] loginTestData1 = loginTestData1();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(loginTestData1[3][0]);
		driver.findElement(By.name("pass")).sendKeys(loginTestData1[3][1]);
		driver.findElement(By.name("login")).click();
	

	}


	private void writeExcel() throws IOException {
		// Create a new workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a new sheet
		XSSFSheet sheet = workbook.createSheet("tosca1");

		// Write some data to the sheet
		String[][] data = { { "Name", "Age", "City" }, { "John", "30", "New York" }, { "Alice", "25", "London" },
				{ "Bob", "35", "Paris" }, { "Name", "Age", "City" }, { "John", "30", "New York" },
				{ "Alice", "25", "London" }, { "Bob", "35", "Paris" }, { "Carol", "28", "Berlin" },
				{ "David", "40", "Rome" }, { "Eva", "22", "Madrid" }, { "Frank", "33", "Tokyo" },
				{ "Grace", "29", "Sydney" }, { "Hank", "45", "Toronto" }, { "Ivy", "26", "Amsterdam" },
				{ "Jack", "31", "Dublin" }, { "Karen", "34", "Vienna" }, { "Leo", "37", "Zurich" },
				{ "Mia", "27", "Stockholm" }, { "Nina", "32", "Copenhagen" }, { "Oscar", "38", "Oslo" },
				{ "Paul", "36", "Helsinki" }, { "Quinn", "39", "Brussels" }, { "Rita", "24", "Prague" },
				{ "Steve", "41", "Athens" }};

		int rowCount = 0;
		for (String[] rowValues : data) {
			XSSFRow row = sheet.createRow(rowCount++);
			int columnCount = 0;
			for (String value : rowValues) {
				XSSFCell cell = row.createCell(columnCount++);
				cell.setCellValue(value);
			}
		}

		// Write the workbook to a file
		File file = new File(
				"C:\\Users\\Lenovo\\eclipse-workspace\\Ganesh\\DataDrivenFramework_Ganesh\\src\\test\\resources\\TestData\\Tricentis_TestData.xlsx");
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

		System.out.println("Excel file created successfully.");

	}

	
}
