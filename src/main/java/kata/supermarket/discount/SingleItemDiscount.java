package kata.supermarket.discount;

import kata.supermarket.Item;

public abstract class SingleItemDiscount implements Discount {

    private Item discountedItem;

    public SingleItemDiscount(Item discountedItem) {
        this.discountedItem = discountedItem;
    }
}
