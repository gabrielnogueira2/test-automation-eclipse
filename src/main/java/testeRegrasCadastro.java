import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Assert;


@RunWith(Parameterized.class)

public class testeRegrasCadastro{
	
	
	private WebDriver driver;
	private DSL dsl;
	private bibliotecaPage page;
	
	@Parameter
	public String nome;
	@Parameter(1)
	public String sobrenome;
	@Parameter(2)
	public String sexo;
	@Parameter(3)
	public List<String> comidas;
	@Parameter(4)
	public String[] esporte;
	@Parameter(5)
	public String msg;

	@Before 
	public void inicializar() {
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new bibliotecaPage(driver);
	}
	
	@After
	public void fechar() {
		driver.quit();
	}
	
	
	@Parameters
	 public static Collection <Object[]> getCollection(){
		return Arrays.asList(new Object [][] {
			{"","","",Arrays.asList(), new String[]{},"Nome eh obrigatorio"},
			{"Gabriel","","",Arrays.asList(), new String[]{},"Sobrenome eh obrigatorio"},
			{"Gabriel","Costa","",Arrays.asList(), new String[]{},"Sexo eh obrigatorio"},
			{"Gabriel","Costa","Masculino",Arrays.asList("Carne","Vegetariano"), new String[]{},"Tem certeza que voce eh vegetariano?"},
			{"Gabriel","Costa","Masculino",Arrays.asList("Carne"), new String[]{"natacao","nada"},"Voce faz esporte ou nao?"},


		});
		
	}
	
	
	
	@Test
	public void deveValidarRegras() { 
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
		page.setSexoMasculino();
		}
		
		if(comidas.contains("Carne"))page.setCarne();
		if(comidas.contains("Vegetariano"))page.setVegetariano();
		if(comidas.contains("Frango"))page.setComida();
		
		page.setEsportes(esporte);
		
		page.setCadastrar();
		Assert.assertEquals(msg, dsl.obterAlertEconfirm());
		
	}


}
