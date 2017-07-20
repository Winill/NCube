package pages;

import org.openqa.selenium.By;

import static appmanager.TestBase.app;

/**
 * Created by Oлег on 19.07.2017.
 */
public class ShopPage {

    private By title = By.xpath("//meta[@name='title']");

    public String getShopTitle(){
       String shopTitle = app.wd.findElement(title).getAttribute("content");
       return shopTitle;
    }
}
