import java.awt.Checkbox;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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






public class treinandoPage {
	private WebDriver driver;
	private DSL dsl;
	private bibliotecaPage page;

	@Before 
	
	public void inicializar() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new bibliotecaPage(driver);
	}
	
	//@After
	public void fechar() {
		driver.quit();
	}
	
	
	
	@Test
	public void cadastro() {
		
		page.setNome("Gabriel");
		page.setSobrenome("Costa");
		page.setSexoMasculino();
		page.setComida();
		page.setEscolaridade("mestrado");
		page.setEsportes("natacao");
		page.setCadastrar();
		
		Assert.assertTrue(page.pegarResultado().startsWith("Cadastrado!"));
		Assert.assertTrue(page.pegarNome().endsWith("Gabriel"));
		Assert.assertEquals("Sobrenome: Costa", page.pegarSobrenome());
		Assert.assertEquals("Sexo: Masculino", page.pegarSexo());
		Assert.assertEquals("Comida: Frango", page.pegarComida());
		Assert.assertEquals("Escolaridade: mestrado", page.pegarEscolaridade());
		Assert.assertEquals("Esportes: Natacao", page.pegarEsporte());

		
		
 		
		/*
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
	*/	
		
	}
	
	
	
	/********* Fazendo a checagem ************/

	@Test
	public void checandoNome() { 
		
		page.setCadastrar();
		Assert.assertEquals("Nome eh obrigatorio",dsl.obterAlertEconfirm());
	}
		
	@Test
	public void checandoSobrenome() { 
		
		page.setNome("Qualquer coisa");
		page.setCadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio",dsl.obterAlertEconfirm());
	}
	
	@Test
	public void checandoSexo() { 
		
		page.setNome("Qualquer coisa");
		page.setSobrenome("Qualquer coisa");
		page.setCadastrar();
		Assert.assertEquals("Sexo eh obrigatorio",dsl.obterAlertEconfirm());
	}
	
	@Test
	public void checandoVegetariano() { 
		
		page.setNome("Qualquer coisa");
		page.setSobrenome("Qualquer coisa");
		page.setSexoMasculino();
		page.setCarne();
		page.setVegetariano();
		page.setCadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.obterAlertEconfirm());

	}
	
	@Test
	public void checandoEsporte() { 
		
		page.setNome("Qualquer coisa");
		page.setSobrenome("Qualquer coisa");
		page.setSexoMasculino();
		page.setCarne();
		page.setEsportes("natacao","nada");
		page.setCadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterAlertEconfirm());
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
