/*
 * Created on 12/11/2006
 */
package selenium.test;

/**
 * @author ccancinos
 */
public class Browser {
	public static String FIRE_FOX="*firefox";
	public static String EXPLORER="*iexplore";
	public static String OPERA="*opera";
	public static String SAFARI="*safari";
	public static String CRHOME="*chrome";
	public static String IEHTA="*iehta";
	public static String CUSTOM="*custom";

    private String browserName;

    Browser(String value) {
        this.browserName = value;
    }

    public String getBrowserName() {
        return this.browserName;
    }
}
