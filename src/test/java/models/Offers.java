package models;

import java.util.Comparator;

/**
 * Created by Oлег on 16.07.2017.
 */
public class Offers {
    private int reviews;
    private int guarantee;
    private double price;
    private String shop;

    public String getShop() {
        return shop;
    }

    public Offers withShop(String shop) {
        this.shop = shop;
        return this;
    }

    public Integer getReviews() {
        return reviews;
    }

    public Offers withResponse(Integer response) {
        this.reviews = response;
        return this;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public Offers withGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Offers withPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offers offers = (Offers) o;

        if (reviews != offers.reviews) return false;
        if (guarantee != offers.guarantee) return false;
        if (Double.compare(offers.price, price) != 0) return false;
        return shop != null ? shop.equals(offers.shop) : offers.shop == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = reviews;
        result = 31 * result + guarantee;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (shop != null ? shop.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "reviews=" + reviews +
                ", guarantee=" + guarantee +
                ", price=" + price +
                ", shop='" + shop + '\'' +
                '}';
    }

    public static Comparator<Offers> byPrice = new Comparator<Offers>() {
        @Override
        public int compare(Offers o1, Offers o2) {
            return (int) (o1.getPrice() - o2.getPrice());
        }
    };

}
