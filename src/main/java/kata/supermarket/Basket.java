package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(new DiscountCalculator().discounts());
        }
    }

    private class DiscountCalculator {

        private Set<Discount> activeDiscounts;

        private DiscountCalculator() {
            this.activeDiscounts = new HashSet<>(
                    Collections.singletonList(
                            new BuyOneGetOneFreeSingleItemDiscount(null)
                    )
            );
        }

        private BigDecimal discounts() {
            return activeDiscounts.stream().map(Discount::calculate).
                    reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    private interface Discount {
        BigDecimal calculate();
    }

    private abstract class SingleItemDiscount implements Discount {

        private Item discountedItem;

        public SingleItemDiscount(Item discountedItem) {
            this.discountedItem = discountedItem;
        }
    }

    private class BuyOneGetOneFreeSingleItemDiscount extends SingleItemDiscount {

        public BuyOneGetOneFreeSingleItemDiscount(Item discountedItem) {
            super(discountedItem);
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
}
