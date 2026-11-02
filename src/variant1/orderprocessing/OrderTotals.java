package variant1.orderprocessing;

import java.math.BigDecimal;

/** Результат розрахунку. */
public record OrderTotals(
        BigDecimal subtotal,
        BigDecimal discountAmount,
        BigDecimal taxAmount,
        BigDecimal total) {
}
