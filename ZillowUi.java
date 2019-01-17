package com.wbl.UiAutomation.UiAutomation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ZillowUi {
	@Test(priority = 0)
	public void method() {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Resources/chromedriver.exe");
		/* ChromeDriver cdriver = new ChromeDriver();
		    HttpCommandExecutor executor = (HttpCommandExecutor) cdriver.getCommandExecutor();
		    URL url = executor.getAddressOfRemoteServer();
		    SessionId session_id = cdriver.getSessionId();


		    RemoteWebDriver driver2 = createDriverFromSession(session_id, url);*/
		WebDriver driver = new ChromeDriver();
		// String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		// driver.findElement(By.linkText("https://www.google.com/")).sendKeys(selectLinkOpeninNewTab);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		// driver.get("https://www.zillow.com/");
		driver.navigate().to("https://www.zillow.com/");
		// driver.manage().window().maximize();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(
				"#pfs-nav-wrapper > div > header > div.znav-content > nav > div.znav-links > ul.znav-links-main > li:nth-child(1) > a > span"))
				.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement areabox = driver.findElement(By.xpath("//*[@id=\'citystatezip\']"));
		areabox.sendKeys("San Francisco");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//
		// driver.findElement(By.xpath("//*[@id=\'yui_3_18_1_1_1546651528584_87\']/div[1]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("zsg-icon-searchglass")));
		element.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * driver.findElement(By.xpath("//ul[1]/li[1]/article[1]/div[1]")).click();
		 * String sa=driver.findElement(By.xpath("//div/header/h1")).getText();
		 * System.out.println(sa);
		 */

		List<WebElement> allHouse = driver.findElements(By.xpath("//*[@class='photo-cards']//li/article/div/div/p[2]"));
		int sizeofhouse = allHouse.size();
		String[] sa = new String[sizeofhouse];
		for (int i = 0; i < sizeofhouse; i++) {
			sa[i] = allHouse.get(i).getText();
			System.out.println(sa[i]);
		}
		int count = 0;
		for (int j = 0; j < sa.length; j++) {
			driver.navigate().to("https://meyerweb.com/eric/tools/dencoder/");
			driver.findElement(By.xpath("//*[@id='dencoder']")).sendKeys(sa[j]);
			driver.findElement(By.xpath("//html/body/form/div/input[2]")).click();
			WebElement ele1 = driver.findElement(By.xpath("//*[@id='dencoder']"));
			ele1.sendKeys(Keys.CONTROL, "a");
			// Thread.sleep(1000);
			ele1.sendKeys(Keys.CONTROL, "c");
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();
			System.out.println(actions.toString() + "paste");

			/*
			 * driver.findElement(By.xpath("//*[@id='dencoder']")).sendKeys(sa);
			 * driver.findElement(By.xpath("/html/body/form/div/input[2]")).click();
			 * driver.findElement(By.xpath("//*[@id='dencoder']"));
			 * driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+ "/t");
			 */
		}
		driver.navigate().back();
	}
	/*public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){
	    CommandExecutor executor = new HttpCommandExecutor(command_executor) {

	    @Override
	    public Response execute(Command command) throws IOException {
	        Response response = null;
	        if (command.getName() == "newSession") {
	            response = new Response();
	            response.setSessionId(sessionId.toString());
	            response.setStatus(0);
	            response.setValue(Collections.<String, String>emptyMap());

	            try {
	                Field commandCodec = null;
	                commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
	                commandCodec.setAccessible(true);
	                commandCodec.set(this, new W3CHttpCommandCodec());

	                Field responseCodec = null;
	                responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
	                responseCodec.setAccessible(true);
	                responseCodec.set(this, new W3CHttpResponseCodec());
	            } catch (NoSuchFieldException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }

	        } else {
	            response = super.execute(command);
	        }
	        return response;
	    }
	    };

	    return new RemoteWebDriver(executor, new DesiredCapabilities());
	}
*/
}

