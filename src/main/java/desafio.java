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


public class desafio {

@Test 

public void testeDesafio() {
	
	
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//Fazendo a procura dos elementos e selecionando eles considerando suas classes 
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
	
	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
	
	driver.findElement(By.id("elementosForm:sexo:0")).click();
		
	driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
	
	WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
	Select combo = new Select(element);
	combo.selectByIndex(0);
	
	//cadastrando o usuario
	driver.findElement(By.id("elementosForm:cadastrar")).click();
	
	//Procurando pelo cadastrado. Foi apresentado uma nova forma de procura. terminando com "cadastrado" no final. 
	Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
	Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Gabriel"));
	
	Assert.assertEquals("Sobrenome: Costa", driver.findElement(By.id("descSobrenome")).getText());
	Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
	Assert.assertEquals("Comida: Frango", driver.findElement(By.id("descComida")).getText());
	Assert.assertEquals("Escolaridade: 1grauincomp", driver.findElement(By.id("descEscolaridade")).getText());
	
	driver.quit();
	
	
	
	
}





}
