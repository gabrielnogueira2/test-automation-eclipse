import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	//construtor do driver
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void escreve(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	public void escreve (String id_campo, String texto) {
		
		//parametrizando os metodos.
		driver.findElement(By.id(id_campo)).sendKeys(texto);
	}

	public String obterValorCampo(String id_campo) {
		
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	
	
	}

	public void clicarRadioButton(String id_click) {
		driver.findElement(By.id(id_click)).click();
	
	}

	public boolean isRadioSelected(String id_click){
		return driver.findElement(By.id(id_click)).isSelected();
	
	}
	
	public void clicarCheckBox(String id) {
		driver.findElement(By.id(id)).click();
	
	}
	
	public boolean isCheckSelected(String id){
		return driver.findElement(By.id(id)).isSelected();
	
	}
	
	public void selecionarCombo(String id, String valor){
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByValue(valor);
		
	}
	
	
	 public String obterValorCombo(String id){
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
		
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();

	}
	
	public void clicarLink(String id) {
		driver.findElement(By.linkText(id)).click();

	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
		
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
		
	}
	
	
	public void switchToFrame(String id) {
		driver.switchTo().frame(id);

	}
	
	public void switchToWindow(String id) {
		driver.switchTo().window(id);

	}
	

	public String obterAlertEconfirm() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String obterAlertEcancel() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}
	
	public void obterAlertEscreve(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
		}
	
	public String alertaObterTexto(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	

	
	
}