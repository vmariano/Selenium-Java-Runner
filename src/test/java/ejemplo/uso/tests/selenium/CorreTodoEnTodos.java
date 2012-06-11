package ejemplo.uso.tests.selenium;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Test;

import selenium.test.Browser;
import selenium.test.suite.AbstractSuite;

public class CorreTodoEnTodos extends AbstractSuite{

	@Override
	protected Collection<String> getBrowsers() {
		Collection<String> browsers = new ArrayList<String>();
		browsers.add(Browser.FIRE_FOX);
		browsers.add(Browser.CRHOME);
		return browsers;
	}

	@Override
	protected String getBaseUrl() {
		return "https://www.google.com.ar/";
	}

	@Override
	protected String getSuiteName() {
		return "Prueba Simple";
	}

	@Override
	protected Collection<Class> getTestSuites() {
		Collection<Class> cases = new ArrayList<Class>();
		cases.add(MiTestDeGoogle.class);
		return cases;
	}
	
	public static Test suite() {
	     AbstractSuite test= new CorreTodoEnTodos();
	     return test.makeSuite();
	 }

}
