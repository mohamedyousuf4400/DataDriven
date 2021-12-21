package logInTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LogInScenario {
	
	String [][] data =null;

	@DataProvider(name="LogInData")
	public String[][] LoginDataProvider() throws BiffException, IOException {
		
		data = getExcelData();
		return data;
	}
	
	public String[][] getExcelData() throws BiffException, IOException  {
		
		FileInputStream excel = new FileInputStream("C:\\Users\\lenovo\\Documents\\dataprovider.xls");
	    
		Workbook workbook = Workbook.getWorkbook(excel);
		
		Sheet sheet = workbook.getSheet(0);
		
		int rowCount = sheet.getRows();
		
		int columnCount = sheet.getColumns();
		
		String testData[][] = new String[rowCount-1][columnCount];
		
		for (int i=1; i<rowCount;i++) {
			for(int j=0; j<columnCount;j++) {
				
				testData[i-1][j] = sheet.getCell(j,i).getContents();
				
			}
		}
		return testData;
	}
	
	@Test(dataProvider = "LogInData")
	public void Login(String Username,String Password) {

		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		//Admin UserName Enter
		WebElement AdminUserName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
		AdminUserName.sendKeys(Username);

		//Admin Password Enter
		WebElement AdminPassword = driver.findElement(By.xpath("//input[@name='txtPassword']"));
		AdminPassword.sendKeys(Password);

		//click Login
		WebElement LogInButton = driver.findElement(By.xpath("//input[@id='btnLogin']"));
		LogInButton.click();
		
		driver.quit();

	}

}
