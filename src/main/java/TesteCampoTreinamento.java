import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCampoTreinamento {

	//criando a variavel global
	private WebDriver driver;
	
		@Before
		public void inicializar(){
			WebDriver driver = new FirefoxDriver();
			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			
		}
		
		@After
		public void fechar() {
			//metodo
			driver.quit();
		}
	
	
	
	
@Test
public void testeTextField() {
	
	WebDriver driver = new FirefoxDriver();
	
	/*Foi necessario ajustar o caminho necessario para acessar o formulario 
	Geralmente e acessado o navegador, mas no exemplo foi necessario baixar dois elementos para que o caminho fosse reconhecido
	Assim que baixados, foi indicado o caminho aonde iriam acessar.
	System.getProperty("user.dir") --->Propriedade que indica aonde o projeto esta rodando.
	*/
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	
	//Ajustando a dimensao da tela
	driver.manage().window().setSize(new Dimension(1200,765));
	
	 /* findElement(By.id("elementosForm:nome")) ---> Nessa parte esta buscando um elemento no formulario
	    sendKeys ---> Escreve no formulario
	*/
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
	/*
	 
	 Assert.assertEquals(1,2) ---> Ele faz a comparacao entre os dois elementos no campo indicado. Entre o que foi escrito e oq deveria estar escrito. 
	 getAttribute("value") ---> Valor que esta dentro do campo 
	 
	 */
	Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	
	
	
	//Fechando o driver
	driver.quit();
}

@Test

public void deveInteragirTextArea() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	driver.manage().window().setSize(new Dimension(1200,765));
	driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
	Assert.assertEquals("Teste",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

}

@Test

public void deveInteragirRadiobutton() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	//Achando o elemento e clicando nele
	driver.findElement(By.id("elementosForm:sexo:0")).click();
	//Achando um elemento e verficando se o mesmo esta selecionado
	Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	
}


@Test

public void deveInteragirCheckBox() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	//Achando o elemento e clicando nele
	driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
	//Achando um elemento e verficando se o mesmo esta selecionado
	Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
	
}

@Test

public void deveInteragirComCombo() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//WebElement e utilizado nesse caso para o tipo de elemento que esta sendo testado. 
	WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
	
	//Selecionando o elemnto 
	Select combo = new Select(element);
	
	//Diferentes formas de selecionar e testar um elemento do ID
	
	//combo.selectByIndex(3); - Por index que comeca no 0,1,2,3...
	//combo.selectByValue("especializacao"); - pelo value dele
	
	combo.selectByVisibleText("Doutorado"); //pela vizualizacao do usuario
	
	//Verificando o que esta selecionado 
	Assert.assertEquals("Doutorado", combo.getFirstSelectedOption().getText());
	
}

@Test

public void deveInteragirTamanhoDoCombo() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//WebElement e utilizado nesse caso para o tipo de elemento que esta sendo testado. 
	WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
	
	//Selecionando o elemento 
	Select combo = new Select(element);
	
	//Verificando o tamanho do combo. Cria uma lista com todas as opcoes do WebElement
	List<WebElement> options = combo.getOptions();
	Assert.assertEquals(8, options.size());
	
	//Logica que verifica se alguma opcao esta dentro do combo
	boolean encontrou = false;
	for(WebElement option : options) {
		if (option.getText().equals("Mestrado")){
			encontrou = true;
			break;
		}
	}
	Assert.assertTrue(encontrou);
	
}


@Test

public void verificarValoresComboMultiplo() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//WebElement e utilizado nesse caso para o tipo de elemento que esta sendo testado. 
	WebElement element = driver.findElement(By.id("elementosForm:esportes"));
	
	//Selecionando o elemento 
	Select combo = new Select(element);
	
	//Selecionando os elementos que estao presentes na opcao desejada
	combo.selectByVisibleText("Natacao");
	combo.selectByVisibleText("Corrida");
	combo.selectByVisibleText("O que eh esporte?");
	
	
	//Verificando se as opcoes que foram marcadas foram iguais a 3. 
	List <WebElement> allSelectedOptions = combo.getAllSelectedOptions();
	Assert.assertEquals(3,allSelectedOptions.size());

	//Descelecionando uma opcao e verificando se opcao retirada foi realmente removida
	combo.deselectByVisibleText("Corrida");
	allSelectedOptions= combo.getAllSelectedOptions();
	Assert.assertEquals(2,allSelectedOptions.size());

	driver.quit();
}

@Test

public void deveInteragircomBotao() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//achando um botao e clicando nele
	driver.findElement(By.id("buttonSimple")).click();
	
	WebElement botao = driver.findElement(By.id("buttonSimple"));
	botao.click();
	
	//verificando se o que esta escrito, e igual ao valor dele no html
	Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
}

@Test

public void deveInteragircomlink() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	//achando um link sem id
	driver.findElement(By.linkText("Voltar")).click();
}

@Test

public void deveInteragircomTexto() {
	WebDriver driver = new FirefoxDriver();
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

	//Fazendo uma busca generica utilizando todo cabecalho, nesse exemplo ele me mostra todo o conteudo do cabecalho.
	System.out.println(driver.findElement(By.tagName("body")).getText());
	
	
	//Nesse teste e verificado e retornado como verdadeiro se existe dentro do cabecalho (nao recomendado)
	//Pois o texto pode estar em qualquer lugar da tela
	Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
	
	
	//Busca por tag baseado na posicao da escrita
	Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
	
	//Buscando por um span. O span no caso e onde nao e indicado aonde tem um cabecalho especifico
	//Assert.assertEquals(" Cuidado onde clica, muitas armadilhas... ", driver.findElement(By.tagName("span")));
	
	//A procura deve ser feita de forma mais especifica 
	Assert.assertEquals(" Cuidado onde clica, muitas armadilhas... ", driver.findElement(By.className("facilAchar")).getText());

}





}
