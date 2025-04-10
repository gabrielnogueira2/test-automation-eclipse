import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TesteGoogle {

@Test
public void teste() {
	
	//WebDriver driver = new ChromeDriver();
    //WebDriver driver = new InternetExplorerDriver();
	WebDriver driver = new FirefoxDriver();
	
	driver.get("https://www.google.com/");
	System.out.println(driver.getTitle());
	Assert.assertEquals("Google", driver.getTitle());
	//Ajustando a dimensao da tela
	driver.manage().window().setSize(new Dimension(1200,765));
	//Fechando o driver
	driver.quit();
}
}
