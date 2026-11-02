package variant1.orderprocessing;

import java.math.BigDecimal;

public record OrderTotals(
        BigDecimal subtotal,
        BigDecimal discountAmount,
        BigDecimal vatRate,
        BigDecimal taxAmount,
        BigDecimal total) {
}
