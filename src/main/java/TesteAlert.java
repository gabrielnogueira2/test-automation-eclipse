import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

	@Test
	public void deveInteragircomalert() {
		
		WebDriver driver = new FirefoxDriver();
	
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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

	
	}

	@Test
	public void deveInteragircomConfirm() {
		
		WebDriver driver = new FirefoxDriver();
	
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
		}

	@Test
	public void deveInteragircomCancel() {
		
		WebDriver driver = new FirefoxDriver();
	
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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

	}

	@Test
	public void deveInteragircomPrompt() {
		
		
		//Foi necessario trocar o browser pois na ultima parte do teste foi econtrado falha de compatibilidade
		WebDriver driver = new ChromeDriver();
	
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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

	}




}
