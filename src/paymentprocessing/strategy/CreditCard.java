package paymentprocessing.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCard implements PaymentMethod {

    public BigDecimal calculateProcessingFee(BigDecimal amount) {
        // 2.5% + $0.30 фіксована комісія
        return amount.multiply(new BigDecimal("0.025"))
                .add(new BigDecimal("0.30"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean execute(PaymentRequest request, BigDecimal fee) {
        System.out.println("[CreditCard] Авторизація картки для користувача " + request.getPayerId() + ". Списано: $"
                + request.getAmount().add(fee));

        return true;
    }

}
