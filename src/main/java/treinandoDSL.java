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

public class treinandoDSL {
		
	//criando a variavel global
	private WebDriver driver;
	private DSL dsl;
	
		@Before
		public void inicializar(){
			driver = new ChromeDriver();
			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			dsl = new DSL(driver);
		}
		
		
		
		@After
		public void fechar() {
			//metodo
			driver.quit();
		}
		
	
	
		@Test
		public void testDSL() {
			
			//Esta chamando a DSL anteriormente criada, achando o parametro e escrevendo.
			//Equivalente a = driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

			dsl.escreve("elementosForm:nome","Teste de escrita");
			
			// Equivalente a = Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

			Assert.assertEquals("Teste de escrita",dsl.obterValorCampo(("elementosForm:nome")));

			
			
			/*Caixa de texto
			driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
			/*
			 
			 Assert.assertEquals(1,2) ---> Ele faz a comparacao entre os dois elementos no campo indicado. Entre o que foi escrito e oq deveria estar escrito. 
			 getAttribute("value") ---> Valor que esta dentro do campo 
			 
			
			Assert.assertEquals("Teste de escrita",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
			*/			
			
			
			//--------------------------------------------------------------------------------------------------------------------------
			//TextBox
			
			dsl.escreve("elementosForm:sugestoes", "Teste");
			Assert.assertEquals("Teste",dsl.obterValorCampo(("elementosForm:sugestoes")));			
			
			/*
			driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
			Assert.assertEquals("Teste",driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));			
			*/
			
			
			//---------------------------------------------------------------------------------------------------------------------------
			//Radio Button 
			
			dsl.clicarRadioButton("elementosForm:sexo:0");
			Assert.assertTrue(dsl.isRadioSelected("elementosForm:sexo:0"));
			
			
			/*
			Achando o elemento e clicando nele
			driver.findElement(By.id("elementosForm:sexo:0")).click();
			
			Achando um elemento e verficando se o mesmo esta selecionado
			Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
			*/
			
			//---------------------------------------------------------------------------------------------------------------------------
			//Check Box
			
			dsl.clicarCheckBox("elementosForm:comidaFavorita:1");
			Assert.assertTrue(dsl.isCheckSelected("elementosForm:comidaFavorita:1"));

		
			/*Achando o elemento e clicando nele
			driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
			//Achando um elemento e verficando se o mesmo esta selecionado
			Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
			*/
			
			//---------------------------------------------------------------------------------------------------------------------------
			//Interagindo com combo
			
			dsl.selecionarCombo("elementosForm:escolaridade","doutorado");
			Assert.assertEquals("doutorado", dsl.obterValorCampo("elementosForm:escolaridade"));
			
			/*
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
			*/
			
			//-------------------------------------------------------------------------------------------------------------------------
			
				
			//combo multiplo
			
			dsl.selecionarCombo("elementosForm:esportes", "natacao");
			dsl.selecionarCombo("elementosForm:esportes", "Corrida");
			dsl.selecionarCombo("elementosForm:esportes", "nada");

		
			WebElement element = driver.findElement(By.id("elementosForm:esportes"));
			
			//Selecionando o elemento 
			Select combo = new Select(element);
			
			
			//Verificando se as opcoes que foram marcadas foram iguais a 3. 
			List <WebElement> allSelectedOptions = combo.getAllSelectedOptions();
			Assert.assertEquals(3,allSelectedOptions.size());

			//Descelecionando uma opcao e verificando se opcao retirada foi realmente removida
			combo.deselectByVisibleText("Corrida");
			allSelectedOptions= combo.getAllSelectedOptions();
			Assert.assertEquals(2,allSelectedOptions.size());
			
			//-------------------------------------------------------------------------------------------------------------------------
			//botao
			
			
			dsl.clicarBotao("buttonSimple");
			
			Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
			
			/*
			 	achando um botao e clicando nele
				driver.findElement(By.id("buttonSimple")).click();
	
				WebElement botao = driver.findElement(By.id("buttonSimple"));
				botao.click();
	
				verificando se o que esta escrito, e igual ao valor dele no html
				Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
			 */
			
		//-----------------------------------------------------------------------------------------------------------------------------
			 
		//achando um link sem id
		
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		
		//-----------------------------------------------------------------------------------------------------------------------------
		
		//interagindo com um texto 
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

		
		}

		@Test
		public void cadastro() {
			
			//Fazendo a procura dos elementos e selecionando eles considerando suas classes 
			
			dsl.escreve("elementosForm:nome", "Gabriel");
			
			//driver.findElement(By.id("elementosForm:nome")).sendKeys("Gabriel");
			
			dsl.escreve("elementosForm:sobrenome", "Costa");
			
			//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Costa");
			
			dsl.clicarRadioButton("elementosForm:sexo:0");
			
			//driver.findElement(By.id("elementosForm:sexo:0")).click();
				
			dsl.clicarCheckBox("elementosForm:comidaFavorita:1");
			
			//driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
			
			dsl.selecionarCombo("elementosForm:escolaridade","1grauincomp");
			
			/*
			WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
			Select combo = new Select(element);
			combo.selectByIndex(0);
			*/
			
			dsl.selecionarCombo("elementosForm:esportes","natacao");

			//cadastrando o usuario
	 		
			dsl.clicarBotao("elementosForm:cadastrar");
				
			
			//driver.findElement(By.id("elementosForm:cadastrar")).click();
			
			//Procurando pelo cadastrado. Foi apresentado uma nova forma de procura. terminando com "cadastrado" no final. 
			
			
			//Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
			
			
			//Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Gabriel"));
			Assert.assertTrue(dsl.obterTexto("resultado"). startsWith("Cadastro"));
			Assert.assertTrue(dsl.obterTexto("descNome"). endsWith("Gabriel"));
			Assert.assertEquals("Sobrenome: Costa", dsl.obterTexto(("descSobrenome")));
			Assert.assertEquals("Sexo: Masculino", dsl.obterTexto(("descSexo")));
			Assert.assertEquals("Comida: Frango", dsl.obterTexto(("descComida")));
			Assert.assertEquals("Escolaridade: 1grauincomp", dsl.obterTexto(("descEscolaridade")));

			
			/*
			Assert.assertEquals("Sobrenome: Costa", driver.findElement(By.id("descSobrenome")).getText());
			Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
			Assert.assertEquals("Comida: Frango", driver.findElement(By.id("descComida")).getText());
			Assert.assertEquals("Escolaridade: 1grauincomp", driver.findElement(By.id("descEscolaridade")).getText());
			*/
			
		}
		
		@Test
		public void testeFrame() {
			
			
			
			dsl.switchToFrame("frame1");
			dsl.clicarBotao("frameButton");
			
		
			String msg = dsl.obterAlertEconfirm();
			Assert.assertEquals("Frame OK!", msg);
		
			driver.switchTo().defaultContent();	
			
			dsl.escreve("elementosForm:nome", msg);
			
			/*
			//Como e um frame, foi necessario fazer a troca. Ficar atento aonde o frame comeca, para achar seu ID. 
			driver.switchTo().frame("frame1");
			
			//Uma vez que o frame foi encontrado, a automacao passa para essa segunda pagina, e assim pode achar o botao.
			//antes de clicar no botao foi preciso encontrar e selecionar o id do frame
			
			//clicando no botao
			driver.findElement(By.id("frameButton")).click();
			
			//Dentro do frame, junto clicando no botao, existe um alert. 
			Alert alert = driver.switchTo().alert();
			//Apos aberto o alert, foi salvo na variavel msg, a mensagem de dentro do Alert
			String msg = alert.getText();
			
			//Verificando a mensagem de dentro do alert.
			Assert.assertEquals("Frame OK!", msg);
			
			//Fechando o alert
			alert.accept();
			
			//Voltando para a pagina principal. Fechando o frame. 
			driver.switchTo().defaultContent();	
			
			//Escrevendo no campo nome, o que estava escrito no alert
			driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
			*/

		}
		
		@Test 

		public void testeJanelaComIdentificador() {
			
			dsl.clicarBotao("buttonPopUpEasy");
			dsl.switchToWindow("Popup");
			dsl.escreve(By.tagName("textarea"),"Deu certo");
			driver.close();
			dsl.switchToWindow("");
			dsl.escreve("elementosForm:sugestoes","E agora");
			
			
			/*
			driver.findElement(By.id("buttonPopUpEasy")).click();
			
			
			//Nem sempre as janelas terao identificador para que seja feita a mudanca, nesse caso exista um identificador
			String windowHandle=driver.getWindowHandle();

			driver.switchTo().window("Popup");
			
			//Apos a janela que foi aberta, esta escrevendo qualquer coisa 
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
			
			//Fechando o pop-up
			driver.close();
			
			//Voltando para a tela sem identificador
			driver.switchTo().window(windowHandle);


			//Escrevendo na area de textos 
			driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("E agora");	
			*/
			
			
			}
			
		
		@Test
		public void testeJanelaSemIdentificador() {
		
			dsl.clicarBotao("buttonPopUpHard");
			System.out.println(driver.getWindowHandle());
			System.out.println(driver.getWindowHandles());
			
			dsl.switchToWindow((String) driver.getWindowHandles().toArray()[1]);
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
			
			dsl.switchToWindow((String) driver.getWindowHandles().toArray()[0]);
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");

			/*
			//clicando no botao
			driver.findElement(By.id("buttonPopUpHard")).click();
			
			//Imprimindo o primeiro ID da janela. Janela principal
			System.out.println(driver.getWindowHandle());
			
			//Imprimindo o primeiro ID da janela. Janela principal e secundaria
			//Importante destacar que o identificador e gerado de forma randomica. Entao toda vez que for rodado o teste, sera gerado um novo numero
			System.out.println(driver.getWindowHandles());
			
			
			
			/* Nessa parte esta sendo feito o cast da identificacao para um array 
			 * Importante ressaltar que na segunda janela, foi utilizado um Handles com S a mais
			 * getWindowHandle ---> 1
			 * getWindowHandles ---> 2
			 * Nessa parte ele esta trocando para a segunda tela
			 
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
			
			//Escrevendo na segunda caixa de texto
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
			
			//Voltando para a tela inicial
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
			
			//Escrevendo na tela principal
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
			*/

		}
			
			
		@Test
		public void deveInteragircomalert() {
			
			dsl.clicarBotao("alert");
			
			String texto = dsl.obterAlertEconfirm();
			
			Assert.assertEquals("Alert Simples", texto);
			
			dsl.escreve("elementosForm:nome", texto);
			
			/*
			//Clicando no botao de alert  
			driver.findElement(By.id("alert")).click();
			 
			//Selenium nao reconhece quando uma janela extra e aberta, portanto precisa mudar pra um switch
			Alert alert = driver.switchTo().alert();
			
			//Pegando o texto do alert, e salvando na variavel texto 
			String texto = alert.getText();
			
			//Apos a troca para a janela externa, pega o texto e verifica o que esta escrito
			Assert.assertEquals("Alert Simples", alert.getText());
			
			//Fechando o alert
			alert.accept();
			
			//Achando o elemento e escrevendo no campo nome, o que estava dentro do alert
			driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
			*/
		
		}

		@Test
		public void deveInteragircomConfirm() {
			
			
			dsl.clicarBotao("confirm");
			
			Assert.assertEquals("Confirm Simples",dsl.obterAlertEconfirm());
			Assert.assertEquals("Confirmado",dsl.obterAlertEconfirm());

			
			
			/*
			//Clicando no botao de alert  
			driver.findElement(By.id("confirm")).click();
			
			//Selenium nao reconhece quando uma janela extra e aberta, portanto precisa mudar pra um switch
			Alert alert = driver.switchTo().alert();
			//Compara com o que esta escrito na caixa de alerta
			Assert.assertEquals("Confirm Simples", alert.getText());
			//CLica no ok
			alert.accept();
			//Compara com o que esta escrito na caixa de alerta
			Assert.assertEquals("Confirmado", alert.getText());
			//CLica no ok
			alert.accept();
			
			*/
			
			}

		@Test
		public void deveInteragircomCancel() {
			
			dsl.clicarBotao("confirm");
			Assert.assertEquals("Confirm Simples", dsl.obterAlertEcancel());
			Assert.assertEquals("Negado", dsl.obterAlertEcancel());
			


			
			
			/*
			//Clicando no botao de alert  
			driver.findElement(By.id("confirm")).click();
			
			//Selenium nao reconhece quando uma janela extra e aberta, portanto precisa mudar pra um switch
			Alert alert = driver.switchTo().alert();
			//Compara com o que esta escrito na caixa de alerta
			Assert.assertEquals("Confirm Simples", alert.getText());
			//CLica no ok
			alert.dismiss();
			//Compara com o que esta escrito na caixa de alerta
			Assert.assertEquals("Negado", alert.getText());
			//CLica no ok
			alert.dismiss();
			*/
		}

		@Test
		public void deveInteragircomPrompt() {
			
			dsl.clicarBotao("prompt");
			Assert.assertEquals("Digite um numero",dsl.alertaObterTexto());
			dsl.obterAlertEscreve("123");
			Assert.assertEquals("Era 123?", dsl.obterAlertEconfirm());
			Assert.assertEquals(":D", dsl.obterAlertEconfirm());
			
			/*
			//Clicando no botao de alert  
			driver.findElement(By.id("prompt")).click();
			
			//Selenium nao reconhece quando uma janela extra e aberta, portanto precisa mudar pra um switch
			Alert alert = driver.switchTo().alert();
			//Identificando apos o alert aberto, o campo que sera escrito
			Assert.assertEquals("Digite um numero", alert.getText());
			
			//Escrevendo no campo 123
			alert.sendKeys("123");
			//Clicando em ok
			alert.accept();
			
			//Abrindo a proxima janela. Verificando o que esta escrito.
			Assert.assertEquals("Era 123?", alert.getText());
			//Apos verificado se o que estava escrito estava certo, clica em ok novamente.
			alert.accept();
			
			//Foi necessario trocar o browser pois na ultima parte do teste foi econtrado falha de compatibilidade
			Assert.assertEquals(":D", alert.getText());
			alert.accept();
			*/
		
		}

		

}
