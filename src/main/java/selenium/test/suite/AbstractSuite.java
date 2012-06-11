package selenium.test.suite;

import junit.framework.TestSuite;
import selenium.test.SeleniumTestSuite;

import java.util.Collection;

public abstract class AbstractSuite {
	
	/**
	 * Needed to run test from maven
	 */
	public void testRunSuite() {
        junit.textui.TestRunner.run(makeSuite());
    }

	public TestSuite makeSuite() {
		SeleniumTestSuite suite;
        suite = new SeleniumTestSuite(getSuiteName(), getBaseUrl());

        //los browsers donde va a correr
        for(String browser : getBrowsers()){
        	suite.addBrowser(browser);
        }
        
         //los tests que va a correr
        for (Class testSuite : getTestSuites()) {
        	suite.addTestSuite(testSuite);
		}
        return suite;
	}

	protected abstract Collection<String> getBrowsers() ;
	protected abstract String getBaseUrl();
	protected abstract String getSuiteName();
	protected abstract Collection<Class> getTestSuites();
}
