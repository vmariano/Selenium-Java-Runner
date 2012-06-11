package selenium.test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import junit.framework.TestCase;
import junit.framework.TestResult;

/**
 * Defines a way of running a test against a Selenium server in a unique
 * browser.
 * <p/>
 * This class should be extended to create single test which can be run
 * individualy in only one browser at a time allowing fast development, or in a
 * SeleniumTestSuite in multiple browsers at a time allowing a much more
 * complete browser compatibility test.
 *
 * @author ccancinos
 */
public abstract class SeleniumTestCase extends TestCase {
    private String browser = "*firefox";
    private int port = 4444;
    private String serverHost = "localhost";
    private Selenium selenium;


    @Override
    protected void setUp() throws Exception {
        this.getSelenium().start();
    }

    @Override
    protected void tearDown() throws Exception {
        this.getSelenium().stop();
    }

    @Override
    public void run(TestResult result) {
        try {
            SeleniumTestServer.getInstance().start();
            this.internalRun(result);
        } catch (RuntimeException exception) {
            exception.printStackTrace();
        } finally {
            SeleniumTestServer.getInstance().stop();
        }
    }

    private void internalRun(TestResult testResult) {
        try {
            if (this.getSelenium() == null) {
                this.setSelenium(this.getSeleniumCommand());
            }
            super.run(testResult);
        } catch (Throwable throwable) {
            // This is to log the error
            testResult.startTest(this);
            testResult.addError(this, throwable);
            testResult.endTest(this);
        }
    }

    // **********************************************************************************************
    // Internal Helppers
    // **********************************************************************************************

    private Selenium getSeleniumCommand() {
        return new DefaultSelenium(this.getServerHost(), this.getPort(), this.getBrowser(), this.getUrl());
    }

    // **********************************************************************************************
    // Information Definition Through Template Methods
    // **********************************************************************************************

    /**
     * Defines the root URL to be tested. Every submit must be inside this URL
     * domain
     */
    abstract protected String getUrl();

    /**
     * Defines the browser where this test will excecuted when this test is
     * running as a stand alone test
     */
    protected String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * The port on which the Selenium Server is listening
     */
    protected int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * The host name on which the Selenium Server resides
     */
    protected String getServerHost() {
        return this.serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    // **********************************************************************************************
    // Accessors
    // **********************************************************************************************

    protected void setSelenium(Selenium selenium) {
        this.selenium = selenium;
    }

    protected Selenium getSelenium() {
        return selenium;
    }
}
