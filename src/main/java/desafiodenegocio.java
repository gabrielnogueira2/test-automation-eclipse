import java.awt.Checkbox;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;




public class desafiodenegocio {
	
	@Test
	public void testNome() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		driver.quit();
		
	}
	

	@Test
	public void testSobrenome() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		driver.quit();
		
		
		
	}

	
	@Test
	public void testSexo() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");

		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		driver.quit();
		
		
		
	}

	@Test
	public void testComida() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

		
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();

		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		driver.quit();
		}

	@Test
	public void testEsporte() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		
		
		
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();

		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());

		
	}
}
