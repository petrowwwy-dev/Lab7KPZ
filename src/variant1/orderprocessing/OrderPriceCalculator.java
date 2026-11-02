package variant1.orderprocessing;

import java.math.BigDecimal;

public class OrderPriceCalculator {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.20");

    public OrderTotals calculate(Order order) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItem item : order.items()) {
            subtotal = subtotal.add(item.lineTotal());
        }

        BigDecimal discountAmount =
                subtotal.multiply(order.category().discountRate());

        BigDecimal taxableSubtotal = subtotal.subtract(discountAmount);
        BigDecimal taxAmount = taxableSubtotal.multiply(VAT_RATE);
        BigDecimal total = taxableSubtotal.add(taxAmount);

        return new OrderTotals(subtotal, discountAmount, VAT_RATE, taxAmount, total);
    }
}
