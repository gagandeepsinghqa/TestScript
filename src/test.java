import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class test {

		static WebDriver Driver;

		public static void main(String[] args)
				throws InterruptedException, AWTException, BiffException, IOException, WriteException, ParseException {
			
			Workbook wb = Workbook.getWorkbook
					(new File("C:\\Users\\kodion Softwares\\workspace\\Infosys\\src\\" + "Test_Data\\test_data.xls"));		
			WritableWorkbook copy = Workbook.createWorkbook(new File("Infosys.csv"), wb);
			WritableSheet sheetToEdit = copy.getSheet("Project");
			Sheet sheet1 = wb.getSheet(0);
			int rows = sheet1.getRows(); 
			System.out.println(rows);

			WritableCell cell1;

			int column = 4;
			int column_2 = 5;
			for (int i = 1; i < rows; i++) {

				// 0th column and 1st row
				jxl.Cell cell = sheet1.getCell(0, i);	
				String contents = cell.getContents().toString();
				if (contents.equalsIgnoreCase("ON")) {

					// 1st column and 1st row
					jxl.Cell vcell = sheet1.getCell(1, i);
					String vTCContents = vcell.getContents().toString();			
					
					if (vTCContents.equals("Chrome")) {
						System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\chromedriver_win32\\gill\\chromedriver.exe");
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--start-maximized");

						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("credentials_enable_service", false);
						prefs.put("profile.password_manager_enabled", false);
						options.setExperimentalOption("prefs", prefs);

						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
						Driver = new ChromeDriver(capabilities);
						Robot r = new Robot();
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_T);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyRelease(KeyEvent.VK_T);				
						
					} else if (vTCContents.equals("Firefox")) {
						System.setProperty("webdriver.gecko.driver", "F:\\Selenium\\Gecko\\geckodriver.exe");
						Driver = new FirefoxDriver();
						Robot r = new Robot();
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_T);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyRelease(KeyEvent.VK_T);							
						
					} else if (vTCContents.equals("Edge")) {
						System.setProperty("webdriver.edge.driver", "F:\\Selenium\\Edge\\MicrosoftWebDriver.exe");
						Driver = new EdgeDriver();
						Robot r = new Robot();
						r.keyPress(KeyEvent.VK_CONTROL);
						r.keyPress(KeyEvent.VK_T);
						r.keyRelease(KeyEvent.VK_CONTROL);
						r.keyRelease(KeyEvent.VK_T);					
					}
					
					jxl.Cell vcell_1 = sheet1.getCell(2, i);
					String vTCContents_1 = vcell_1.getContents().toString();

					Date date = new Date();
					switch (vTCContents_1) {				
					
					case "home_Page_Landed_Correctly":
					
						jxl.Cell vcell12 = sheet1.getCell(3, i);
						String vTCContents2 = vcell12.getContents().toString();
						System.out.println(vTCContents2);
						
						Driver.get(Object_Repository.HOME_PAGE);
						Driver.manage().window().maximize();
						Thread.sleep(15000);	
						
						Driver.findElement(By.linkText("Sign in")).click();
						Driver.findElement(By.name(Object_Repository.Email)).sendKeys("gagangill6500@gmail.com");
						Driver.findElement(By.name(Object_Repository.Password)).sendKeys("gagan@12345");
						Driver.findElement(By.xpath("//*[@id='SubmitLogin']")).click();	
						Thread.sleep(10000);
						
						String g = Driver.findElement(By.name(Object_Repository.home_Page_Landed_Correctly)).getText();
						System.out.println(g);
							System.out.println("test");
							
							if (g.equalsIgnoreCase(vTCContents2)) {
								System.out.println("pass");
								Write_Data(column, i, sheetToEdit, "WORKING");
							} else {
								Write_Data(column, i, sheetToEdit, "NOT WORKING");
							}
							Write_Data(column_2, i, sheetToEdit, date.toString());
							break;			
													
					case "Order_Confirmation":
					 				
						jxl.Cell vcell13 = sheet1.getCell(3, i);
						String vTCContents3 = vcell13.getContents().toString();
						System.out.println(vTCContents3);
						
					    Driver.get("http://automationpractice.com/index.php?id_category=5&controller=category");
						JavascriptExecutor jse_3 = (JavascriptExecutor) Driver;
						jse_3.executeScript("scroll(0, 400);");
						
					    Thread.sleep(5000);				    
						Driver.findElement(By.linkText("List")).click();				
						Driver.findElement(By.linkText("Add to cart")).click();							
						
						Thread.sleep(5000);
						Driver.findElement(By.xpath(Object_Repository.Order_conf_1)).click();	
											
						Thread.sleep(8000);
						
						JavascriptExecutor jse_4 = (JavascriptExecutor) Driver;
						jse_4.executeScript("scroll(0, 800);");
							
						Driver.findElement(By.xpath(Object_Repository.Order_conf_2)).click();
						Driver.findElement(By.xpath(Object_Repository.order_conf_3)).click();
						Driver.findElement(By.name("cgv")).click();
						Thread.sleep(5000);
						
						Driver.findElement(By.xpath("//*[@id='form']/p/button")).click();
						Driver.findElement(By.xpath("//*[@id='HOOK_PAYMENT']/div[2]/div/p/a")).click();
						Driver.findElement(By.xpath("//*[@id='cart_navigation']/button")).click();
						
					    String j =	Driver.findElement(By.xpath("//*[@id='center_column']/h1")).getText();				
					    System.out.println(j);
					
				
					    if (j.equalsIgnoreCase("Order confirmation")) {
							System.out.println("pass");
							Write_Data(column, i, sheetToEdit, "WORKING");
						} else {
							Write_Data(column, i, sheetToEdit, "NOT WORKING");
						}
						Write_Data(column_2, i, sheetToEdit, date.toString());
						break;				
						
						
						
					case "Order_History_Confirmation":
		 				
						jxl.Cell vcell14 = sheet1.getCell(3, i);
						String vTCContents4 = vcell14.getContents().toString();
						System.out.println(vTCContents4);
						
						
						JavascriptExecutor jse_5 = (JavascriptExecutor) Driver;
						jse_5.executeScript("scroll(0, 400);");

						String f = Driver.findElement(By.xpath("//*[@id='center_column']/div")).getText();					
						   System.out.println(f.substring(146));
					
						Driver.get("http://automationpractice.com/index.php?controller=history");
						
					    String b =	Driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr[1]/td[1]/a")).getText();
						System.out.println(b);
								
					if (f.contains(b)) {
							System.out.println("pass");
							Write_Data(column, i, sheetToEdit, "WORKING");
						} else {
							Write_Data(column, i, sheetToEdit, "NOT WORKING");
						}
						Write_Data(column_2, i, sheetToEdit, date.toString());
						break;				
						
					
					case "Edit_Information":
						
						jxl.Cell vcell15 = sheet1.getCell(3, i);
						String vTCContents5 = vcell15.getContents().toString();
						System.out.println(vTCContents5);
						
						Driver.get("http://automationpractice.com/index.php?controller=identity");			
						Driver.findElement(By.name("old_passwd")).sendKeys("gagan@12345");
						Driver.findElement(By.name("passwd")).sendKeys("gagan@123456");
						Driver.findElement(By.name("confirmation")).sendKeys("gagan@123456");
						Driver.findElement(By.xpath("//*[@id='center_column']/div/form/fieldset/div[11]/button/span")).click();

				String v = Driver.findElement(By.xpath("//*[@id='identity']")).getText();
						System.out.println(v);
						if (v.contains("Your personal information has been successfully updated")) {
							System.out.println("pass");
							Write_Data(column, i, sheetToEdit, "WORKING");
						} else {
							Write_Data(column, i, sheetToEdit, "NOT WORKING");
						}
						Write_Data(column_2, i, sheetToEdit, date.toString());	
						break;
					
     				}				
				}
			}
			
			copy.write();
			wb.close();
			copy.close();
			String html = Read_Results.results();
			Email.SendEmail(html);
		}

		public static String Write_Data(int column, int i, WritableSheet sheetToEdit, String value) {

			try {
				Label l = new Label(column, i, value);
				WritableCell cell1 = (WritableCell) l;
				sheetToEdit.addCell(cell1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
