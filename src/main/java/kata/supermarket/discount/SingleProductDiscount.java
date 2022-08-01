package kata.supermarket.discount;

import kata.supermarket.Product;

public abstract class SingleProductDiscount implements Discount {

    protected Product discountedProduct;

    public SingleProductDiscount(Product discountedProduct) {
        this.discountedProduct = discountedProduct;
    }
}
