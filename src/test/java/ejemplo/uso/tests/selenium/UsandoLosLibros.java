package ejemplo.uso.tests.selenium;

import selenium.test.SeleniumTestCase;

public class UsandoLosLibros extends SeleniumTestCase{

	@Override
	protected String getUrl() {
		return "http://localhost:8080/libros-ui-jsp-el/";
	}
	
	public void testBuscarLibroEncontrandolo() throws Exception{
		this.getSelenium().open(this.getUrl());
		this.getSelenium().type("name=titulo", "venas");
		this.getSelenium().click("id=btnBuscarLibro");
		Thread.sleep(3000);
		assertTrue(getSelenium().isTextPresent("Las venas abiertas de América Latina"));
		this.getSelenium().click("link=Las venas abiertas de América Latina");
		Thread.sleep(3000);
		assertTrue(getSelenium().isTextPresent("Detalle de libro"));
	}
	
	public void testBuscarCaseInsensitive() throws Exception{
		this.getSelenium().open(this.getUrl());
		this.getSelenium().type("name=titulo", "Venas");
		this.getSelenium().click("id=btnBuscarLibro");
		Thread.sleep(3000);
		assertFalse(getSelenium().isTextPresent("Las venas abiertas de América Latina"));
	}
	
	

}
