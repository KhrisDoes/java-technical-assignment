package kata.supermarket.discount;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

import java.math.BigDecimal;
import java.util.List;

public class BuyOneGetOneFreeSingleProductDiscount extends SingleProductDiscount {

    private final List<Item> items;

    public BuyOneGetOneFreeSingleProductDiscount(List<Item> items, Product discountedProduct) {
        super(discountedProduct);
        this.items = items;
    }

    @Override
    public BigDecimal calculate() {
        long countOfEligibleItems = items.stream().filter(
                ItemByUnit.class::isInstance
        ).filter(
                item -> ((ItemByUnit) item).getProduct().equals(discountedProduct)
        ).count();

        long numberOfDiscountedItems = countOfEligibleItems / 2;

        return discountedProduct.pricePerUnit().multiply(BigDecimal.valueOf(numberOfDiscountedItems));
    }
}
