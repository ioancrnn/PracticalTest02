package ro.pub.cs.systems.eim.practicaltest02;

import androidx.annotation.NonNull;

public class BitcoinPriceInformation {
    private final String currency;
    private final String price;


    public BitcoinPriceInformation(String currency, String price) {
        this.currency = currency;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bitcoin price{" + "price='" + price + ' ' + ", currency='" + currency + '}';
    }
}
