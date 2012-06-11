package selenium.test;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.thoughtworks.selenium.Selenium;

/**
 * This class is in charge of run test using a particular Selenium instance, which results in test running in a specific browser.
 * 
 * @author ccancinos
 */
public class SeleniumBrowserRunnerTestSuite extends TestSuite {
    private Selenium selenium;

    public SeleniumBrowserRunnerTestSuite(Class testClass, String name) {
        super(testClass, name);
    }

    public SeleniumBrowserRunnerTestSuite(Class testClass, Selenium selenium, String name) {
        this(testClass, name);
        this.selenium = selenium;
    }

    public SeleniumBrowserRunnerTestSuite(Test test, Selenium selenium, String name) {
        super(name);
        addTest(test);
        this.selenium = selenium;
    }

    public void runTest(Test test, TestResult result) {
        if (test instanceof SeleniumTestCase) {
            SeleniumTestCase seleniumTest = (SeleniumTestCase) test;
            seleniumTest.setSelenium(selenium);
        }
        test.run(result);
    }

    public Selenium getSelenium() {
        return this.selenium;
    }

    public void setSelenium(Selenium selenium) {
        this.selenium = selenium;
    }
}
