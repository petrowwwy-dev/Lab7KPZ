package paymentprocessing.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ДЕМО розширення: доданий без змін у PaymentProcessor та в інших способах оплати.
 * Комісія 1.5% вигадана для демонстрації, у завданні такого правила нема.
 */
public class ApplePay implements PaymentMethod {

    @Override
    public BigDecimal calculateProcessingFee(BigDecimal amount) {
        // 1.5% комісія
        return amount.multiply(new BigDecimal("0.015"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean execute(PaymentRequest request, BigDecimal fee) {
        System.out.println("[ApplePay] Токенізована оплата для " + request.getPayerId()
                + ". Комісія системи: $" + fee);
        return true;
    }
}
