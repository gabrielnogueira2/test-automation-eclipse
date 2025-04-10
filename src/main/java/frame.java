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


public class frame {
	@Test 

	public void testeFrame() {
		
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
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
	
	
	
	}
	
	
	
}
