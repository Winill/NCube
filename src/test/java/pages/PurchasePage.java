package pages;

import models.Offers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static appmanager.TestBase.app;
import static models.Offers.byPrice;

/**
 * Created by Олег Калужин on 14.07.2017.
 */
public class PurchasePage {

    private By allOffersButton = By.xpath("//div[contains(@class, 'product-page-nav')]//li[@data-id='prices']");
    private String allOffers = "//div[@data-selector='price-line' and @style='display: block;']";
    private String shopName = "//div[contains(@class, 'shop-title')]";
    private String reviewsCount = "//*[contains(text(), 'Отзывов')]";
    private String monthOfGuarantee = "//span[contains(text(), 'Гарантия')]/parent::div";
    private String priceOfPhones = "//a[@id='gotoshop-price']";
    private String mostCheaplyShop;
    private String cheapestPrice;

    public void chooseAllOffers() {
        app.wd.findElement(allOffersButton).click();
    }

    public List<Offers> offersByParameters() throws InterruptedException {
        List<Offers> offers = new ArrayList<Offers>();
        List<WebElement> elements = app.wd.findElements(By.xpath(allOffers));
        elements.size();
        System.out.println("Кол-во всех элементов, без удаления " + elements.size());
        for (int i = 1; i < elements.size(); i++) {
            String shop = app.wd.findElement(By.xpath(allOffers + "[" + i + "]" + shopName)).getAttribute("title");
            int reviews = value(app.wd.findElement(By.xpath(allOffers + "[" + i + "]" + reviewsCount)).getText());
            int guarantee = value(app.wd.findElement(By.xpath(allOffers + "[" + i + "]" + monthOfGuarantee)).getText());
            double price = valueOfPrice(allOffers + "[" + i + "]" + priceOfPhones);
            Offers item = new Offers().withShop(shop).withResponse(reviews).withGuarantee(guarantee).withPrice(price);
            offers.add(item);
            for (int n = 0; n < elements.size(); n++) {
                if (item.getReviews() < 10 || item.getGuarantee() < 6) {
                    offers.remove(item);
                }
            }
        }
        return offers;
    }

    public void sortByPrice(List<Offers> offers) {
        offers.sort(byPrice);

    }

    public String shopName(List<Offers> offers) {
        shopName = offers.get(0).getShop().toLowerCase().replaceAll(" ", "");
        return shopName;
    }

    public void getCheapestPrice(List<Offers> offers) {
        cheapestPrice = String.valueOf(offers.get(0).getPrice()).substring(2, 5);
    }

    public void getCheapestShop(List<Offers> offers) {
        mostCheaplyShop = offers.get(0).getShop();
    }

    public void goToShop() {
        WebElement buyButton = app.wd.findElement(By.xpath(allOffers + "//div[@title='" + mostCheaplyShop +
                "']/parent::*/parent::*/parent::*//a[contains(text(), '" + cheapestPrice + "')]"));
        buyButton.click();
    }

    private Integer value(String str) {
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(str);
        String result;
        if (m.find()) {
            result = (m.group());
        } else {
            result = "0";
        }
        return Integer.valueOf(result);
    }

    private double valueOfPrice(String xpath) {
        double v;
        String p = app.wd.findElement(By.xpath(xpath))
                .getText().replaceAll(" ", "").replaceAll(",", ".");
        if (p.equals("")) {
            return 0;
        } else {
            v = Double.parseDouble(p);
        }
        return v;
    }

}
