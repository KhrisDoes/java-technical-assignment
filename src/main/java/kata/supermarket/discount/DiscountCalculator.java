package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiscountCalculator {

    private Set<Discount> activeDiscounts;

    public DiscountCalculator(List<Item> items) {
        this.activeDiscounts = new HashSet<>(
                Collections.singletonList(
                        new BuyOneGetOneFreeSingleProductDiscount(items, new Product(new BigDecimal("0.49")))
                )
        );
    }

    public BigDecimal calculateTotalDiscount() {
        return activeDiscounts.stream().map(Discount::calculate).
                reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
