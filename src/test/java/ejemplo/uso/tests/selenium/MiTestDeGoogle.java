package ejemplo.uso.tests.selenium;

import selenium.test.SeleniumTestCase;

public class MiTestDeGoogle extends SeleniumTestCase {

    @Override
    protected String getUrl() {
        return "https://www.google.com.ar/";
    }

    public void testLoMismoDelIDE() throws Exception {
        getSelenium().open(this.getUrl());
        getSelenium().type("id=gbqfq", "Selenium");
        Thread.sleep(4000);
        assertTrue(getSelenium().isTextPresent("Selenium - Web Browser Automation"));
    }
}
