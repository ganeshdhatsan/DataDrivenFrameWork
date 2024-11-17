package DataDrivenFramework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteData {
	private void method() throws IOException {
		
		XSSFWorkbook book = new XSSFWorkbook();
		// Example: Write data to a cell

		XSSFSheet sheet = book.createSheet("Sheet1");
		XSSFRow row = sheet.createRow(0); // Row 0 is the first row
		XSSFCell cell = row.createCell(0); // Column 0 is the first column
		
		XSSFSheet sheet1 = book.createSheet("Sheet2");

		cell.setCellValue("Hello, World!");

		XSSFRow row1 = sheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);

		cell1.setCellValue("John");
		XSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue(30);


		XSSFRow row2 = sheet.createRow(2);
		XSSFCell cell3 = row2.createCell(0);
		cell3.setCellValue("Mary");


		XSSFCell cell4 = row2.createCell(1);
		cell4.setCellValue(25);


//		5.  Save the workbook: Finally, you need to save the changes to the workbook by writing it to a file or a stream.

		FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Ganesh\\DataDrivenFramework_Ganesh\\src\\test\\resources\\TestData\\Tricentis1_TestData.xlsx"));
		book.write(outputStream);
		book.close();
		outputStream.close();
	}
	public static void main(String[] args) throws IOException {
		WriteData data = new WriteData();
		data.method();
	}

}
