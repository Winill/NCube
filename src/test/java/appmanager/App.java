package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oлег on 05.07.2017.
 */
public class App {
    public WebDriver wd;

    void init(String browsers) throws Exception{

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        if(browsers.equalsIgnoreCase("firefox")){
            wd = new FirefoxDriver(capabilities);
        }else if (browsers.equalsIgnoreCase("chrome")) {
            wd = new ChromeDriver();
        }
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
}
