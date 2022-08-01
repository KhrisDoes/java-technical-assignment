package kata.supermarket.discount;

import kata.supermarket.Product;

public abstract class SingleProductDiscount implements Discount {

    private Product discountedProduct;

    public SingleProductDiscount(Product discountedProduct) {
        this.discountedProduct = discountedProduct;
    }
}
