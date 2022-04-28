package org.black_ixx.bossshop.inbuiltaddons.logictypes;

import org.black_ixx.bossshop.core.prices.BSPriceType;

public class BSPricePart {

    private final BSPriceType pricetype;
    private final Object price;

    public BSPricePart(BSPriceType pricetype, Object price) {
        this.price = price;
        this.pricetype = pricetype;
    }

    public BSPriceType getPriceType() {
        return pricetype;
    }

    public Object getPrice() {
        return price;
    }
}
