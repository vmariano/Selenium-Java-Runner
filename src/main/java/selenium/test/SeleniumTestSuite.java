package selenium.test;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * Suite of selenium test suit in charge of run every test in every browser defined.
 * 
 * @author ccancinos
 */
//public class SeleniumTestSuite extends ParallelTestSuite{
//this is a test, needs more time, to easily make parallel tests
public class SeleniumTestSuite extends TestSuite{

    private String url;
    private int port = 4444;
    private String serverHost = "localhost";
    private Collection<String> browsers = new ArrayList<String>();

    // **********************************************************************************************
    // Constructores
    // **********************************************************************************************

    public SeleniumTestSuite(String url) {
        this.url = url;
    }

    public SeleniumTestSuite(String testName, String url) {
        super(testName);
        this.url = url;
    }

    public SeleniumTestSuite(String testName, String url, Browser ... browsers) {
        super(testName);
        this.url = url;
        this.addBrowser(browsers);
    }

    // **********************************************************************************************
    // Overrides
    // **********************************************************************************************

    public void run(TestResult testResult) {
        try {
            this.beforeRunSuit();
            super.run(testResult);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            testResult.startTest(this);
            testResult.addError(this, throwable);
            testResult.endTest(this);
        } finally {
            try {
                this.afterRunSuit();
            } catch (Throwable throwable) {
            }
        }

    }

    
    public void addTest(Test test) {
        TestSuite root = new TestSuite(test.getClass().getName());
        for (String browser : this.browsers) {
            root.addTest(new SeleniumBrowserRunnerTestSuite(test, this.createSelenium(browser), browser));
        }
        super.addTest(root);
    }

    /**
     * Each test added will be run on each browser this suite is configured to run his test. 
     */
    public void addTestSuite(Class testClass) {
        TestSuite root = new TestSuite(testClass.getName());
        for (String browser : this.browsers) {
            root.addTest(new SeleniumBrowserRunnerTestSuite(testClass, this.createSelenium(browser), browser));
        }
        super.addTest(root);
    }

    private Selenium createSelenium(String browser) {
        return new DefaultSelenium(this.serverHost, this.port, browser, this.url);
    }

    // **********************************************************************************************
    // Template Method
    // **********************************************************************************************

    /**
     * Method invoked before running the entire suite
     */
    protected void beforeRunSuit() {
    }

    /**
     * Method invoked after running the entire suite
     */
    protected void afterRunSuit() {
    }

    // **********************************************************************************************
    // Accessors
    // **********************************************************************************************

    /**
     * Adds a browser where every test of this suite will be run
     */
    public SeleniumTestSuite addBrowser(String browser) {
        this.browsers.add(browser);
        return this;
    }
    
    /**
     * Adds a custom browser where every test of this suite will be run
     */
    public SeleniumTestSuite addBrowserCustom(String browser) {
        this.browsers.add(Browser.CUSTOM + " "+ browser);
        return this;
    }
    
    /**
     * Adds browsers where every test of this suite will be run
     */
    public void addBrowser(Browser ... browsers) {
    	for (Browser browser : browsers) {
			this.addBrowser(browser);
		}
    }

    /**
     * Gets the host name on which the Selenium Server resides
     */
    public String getServerHost() {
        return this.serverHost;
    }

    /**
     * Sets the host name on which the Selenium Server resides
     */
    public void setServerHost(String host) {
        this.serverHost = host;
    }

    /**
     * The port on which the Selenium Server is listening
     */
    public int getPort() {
        return this.port;
    }

    /**
     * The port on which the Selenium Server is listening
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * Defines the root URL to be tested. Every submit must be inside this URL domain 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The root URL to be tested. Every submit must be inside this URL domain 
     */
    public String getUrl() {
        return this.url;
    }

}
