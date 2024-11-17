package DataDrivenFramework;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderConcept {
		
	    private WebDriver driver;
	    
	    private String[][] loginTestData() throws IOException {
			DataFormatter format = new DataFormatter();
			File file = new File(System.getProperty("user.dir") + "\\Login Data.xlsx");
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
	    
	    @Test
		private void validateLoginWithInvalidUserName() throws IOException {
			String[][] loginTestData1 = loginTestData();

			driver.get("https://www.facebook.com/");
			driver.findElement(By.id("email")).sendKeys(loginTestData1[1][0]);
			driver.findElement(By.name("pass")).sendKeys(loginTestData1[1][1]);
			driver.findElement(By.name("login")).click();
		}

		@Test
		private void validateLoginWithValidCredential() throws IOException {

			String[][] loginTestData1 = loginTestData();
			System.out.println("Row size" + loginTestData1.length);
			System.out.println("Cell size" + loginTestData1[0].length);
			for (int i = 0; i < loginTestData1.length; i++) {
				for (int j = 0; j < loginTestData1[i].length - 1; j++) {
					String userName = loginTestData1[i][j];
					System.out.println(" Username" + userName);
					String password = loginTestData1[i][j + 1];
					System.out.println(" password" + password);
					driver.get("https://www.facebook.com/");
					if (userName != null) {
						driver.findElement(By.id("email")).sendKeys(userName);
						System.out.println(i + " : " + j);
					}
					if (password != null) {
						driver.findElement(By.name("pass")).sendKeys(password);
						System.out.println(i + " : " + (j + 1));
					}
					driver.findElement(By.name("login")).click();
//					driver.switchTo().newWindow(WindowType.TAB);
				}
			}
		}
		

	    @BeforeClass
	    public void browserLaunch() {
	        // Initialize the ChromeDriver only once before all tests
	        driver = new ChromeDriver();
	    }

	    @BeforeMethod
	    public void openNewTab() {
	        // Open a new tab before each test
	        driver.switchTo().newWindow(WindowType.TAB);
	    }

	    @Test(dataProvider = "loginTest")
	    public void validateLogin(String[] logindata) throws IOException {
	        driver.get("https://www.facebook.com/");
	        
	        String userName = logindata[0];
	        String password = logindata[1];
	        if (userName != null) {
	            driver.findElement(By.id("email")).sendKeys(userName);
	        }
	        if (password != null) {
	            driver.findElement(By.name("pass")).sendKeys(password);
	        }
	        driver.findElement(By.name("login")).click();
	    }

	   @DataProvider(name="loginTest")
		private String[][] testData() throws IOException {

			String[][] loginTestData = loginTestData();

			return loginTestData;
		}

	    @AfterMethod
	    public void closeCurrentTab() {
	        // Close the current tab after each test
	        driver.close();
	        // Switch back to the original tab if needed
//	        driver.switchTo().window(driver.getWindowHandles().iterator().next());
	    }

	    @AfterClass
	    public void tearDown() {
	        // Quit the driver after all tests
	        driver.quit();
	    }

	   
	}



