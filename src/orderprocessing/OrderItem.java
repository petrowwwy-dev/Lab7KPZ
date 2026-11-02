package orderprocessing;

import java.math.BigDecimal;

public record OrderItem(String itemId, String name, BigDecimal price, int quantity) {

    public BigDecimal lineTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
