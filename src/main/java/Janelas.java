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




public class Janelas {

	@Test 

	public void testeJanelaComIdentificador() {
		
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
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
		
		
		}
	
	
	
	@Test
	public void testeJanelaSemIdentificador() {
	
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		//clicando no botao
		driver.findElement(By.id("buttonPopUpHard")).click();
		
		//Imprimindo o primeiro ID da janela. Janela principal
		System.out.println(driver.getWindowHandle());
		
		//Imprimindo o primeiro ID da janela. Janela principal e secundaria
		//Importante destacar que o identificador e gerado de forma randomica. Entao toda vez que for rodado o teste, sera gerado um novo numero
		System.out.println(driver.getWindowHandles());
		
		
		
		/* Nessa parte esta sendo feito o cast da identificacao para um array 
		 * Importante ressaltar que no segunda segunda janela, foi utilizado um Handles com S a mais
		 * getWindowHandle ---> 1
		 * getWindowHandles ---> 2
		 * Nessa parte ele esta trocando para a segunda tela
		 */
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		
		//Escrevendo na segunda caixa de texto
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
		
		//Voltando para a tela inicial
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		
		//Escrevendo na tela principal
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");

	
	
	
	}
		
		
	


}





