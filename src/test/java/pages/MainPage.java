package pages;

import appmanager.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;


/**
 * Created by Олег Калужин on 05.07.2017.
 */
public class MainPage extends TestBase{

  private Actions action = new Actions(app.wd);

  private By searchBox = By.xpath("//input[@id='searchbox']");
  private By iphone = By.partialLinkText("Apple iPhone 7");

  public void findIphone(String searchWord) {
    app.wd.findElement(searchBox).sendKeys(searchWord);
    action.sendKeys(Keys.BACK_SPACE).build().perform();
    app.wd.findElement(iphone).click();
  }





}
