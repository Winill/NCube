package tests;

import appmanager.TestBase;
import models.Offers;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PurchasePage;
import pages.ShopPage;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by Олег Калужин on 14.07.2017.
 */
public class HotLineTest extends TestBase {

    private MainPage mainPage;
    private PurchasePage purchasePage;
    private ShopPage shopPage;

    @Test(enabled = true)
    public void testHotLine() throws InterruptedException {
        app.wd.get("http://hotline.ua/");
        mainPage = new MainPage();
        mainPage.findIphone("Apple Iphone 7");
        purchasePage = new PurchasePage();
        purchasePage.chooseAllOffers();
        List<Offers> offers = purchasePage.offersByParameters();
        purchasePage.sortByPrice(offers);
        purchasePage.getCheapestShop(offers);
        purchasePage.getCheapestPrice(offers);
        purchasePage.goToShop();
        shopPage = new ShopPage();

        assertFalse(app.wd.getCurrentUrl().contains("http://hotline.ua/"));
        assertTrue(shopPage.getShopTitle().contains(purchasePage.shopName(offers)));
    }
}
