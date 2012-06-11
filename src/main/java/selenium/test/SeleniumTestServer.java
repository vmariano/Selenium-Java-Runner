package selenium.test;

import org.openqa.selenium.server.SeleniumServer;

/**
 * Singleton del servidor de selenium
 * 
 * @author ccancinos
 */
public class SeleniumTestServer {
    private static SeleniumTestServer instance;

    private SeleniumServer selenium;

    private boolean isStarted = false;

    private SeleniumTestServer() {
        try {
            this.selenium = new SeleniumServer();
        } catch (Exception exception) {
            throw new SeleniumTestException("The selenium server cannot be created", exception);
        }
    }

    public static synchronized SeleniumTestServer getInstance() {
        if (instance == null) {
            instance = new SeleniumTestServer();
        }
        return instance;
    }

    // TODO Start and Stop are called in SeleniumTestCase#run method.
    // Find a way of call them only once when a testsuite is being run, and not
    // once per testcase in a testsuite, but stil call it from a testcase when
    // it is being run stand alone

    public void start() {
        try {
            if (!this.isStarted) {
                this.selenium.start();
                this.isStarted = true;
            }
        } catch (Exception exception) {
            throw new SeleniumTestException("The selenium server cannot be started", exception);
        }
    }

    public void stop() {
        if (this.isStarted) {
		    this.selenium.stop();
		    this.isStarted = false;
		}
    }

}
