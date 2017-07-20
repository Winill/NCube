package appmanager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


/**
 * Created by Олег Калужин on 05.07.2017.
 */
public class TestBase {
    public static final App app = new App();

    @Parameters("browsers")
    @BeforeMethod
    public void setUp(String browsers) throws Exception {
        app.init(browsers);

    }
    @AfterMethod
    public void tearDown() {
        app.wd.quit();
    }

}
