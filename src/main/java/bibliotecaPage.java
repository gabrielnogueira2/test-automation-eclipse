import org.openqa.selenium.WebDriver;

public class bibliotecaPage {
	
	private DSL dsl;
	
	
	//construtor
	public bibliotecaPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	/********* Cadastrando ************/

	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}
	public bibliotecaPage(DSL dsl) {
		super();
		this.dsl = dsl;
	}
	public void setSobrenome(String nome) {
		dsl.escreve("elementosForm:sobrenome", nome);
	}
	public void setSexoMasculino() {
		dsl.clicarRadioButton("elementosForm:sexo:0");;
	}
	
	
	public void setSexoFeminino() {
		dsl.clicarRadioButton("elementosForm:sexo:1");;
	}
	
	
	public void setComida() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:1");
	}
	
	public void setCarne() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
	}
	
	public void setVegetariano() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	
	public void setEsportes(String...valores) {
		//laco
		for(String valor:valores)
			dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	/********* Checando cadastro ************/
	
	public void setCadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String pegarResultado() {
		return dsl.obterTexto("resultado");
	}
	
	public String pegarNome() {
		return dsl.obterTexto("descNome");
	}
	public String pegarSobrenome() {
		return dsl.obterTexto("descSobrenome");
	}
	public String pegarSexo() {
		return dsl.obterTexto("descSexo");
	}
	public String pegarComida() {
		return dsl.obterTexto("descComida");
	}
	public String pegarEscolaridade() {
		return dsl.obterTexto("descEscolaridade");
	}
	public String pegarEsporte() {
		return dsl.obterTexto("descEsportes");
	
	}

}
