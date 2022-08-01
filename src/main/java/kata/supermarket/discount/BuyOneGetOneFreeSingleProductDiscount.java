package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuyOneGetOneFreeSingleProductDiscount extends SingleProductDiscount {

    private final List<Item> items;

    public BuyOneGetOneFreeSingleProductDiscount(List<Item> items, Product discountedProduct) {
        super(discountedProduct);
        this.items = items;
    }

    @Override
    public BigDecimal calculate() {
        Set<BigDecimal> seenItemPrice = new HashSet<>();
        for (Item item : items) {
            // Find seen item by price - assuming no different items have the same price
            if (seenItemPrice.contains(item.price())) {
                return item.price();
            }
            seenItemPrice.add(item.price());
        }
        return BigDecimal.ZERO;
    }
}
