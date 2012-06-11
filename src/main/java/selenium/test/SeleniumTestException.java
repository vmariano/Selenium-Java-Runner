package selenium.test;

/**
 * @author ccancinos
 */
public class SeleniumTestException extends RuntimeException {

    public SeleniumTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeleniumTestException(String messge) {
        super(messge);
    }
}
