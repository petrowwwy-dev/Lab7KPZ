package variant1.paymentprocessing.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayPal implements PaymentMethod {

    public BigDecimal calculateProcessingFee(BigDecimal amount) {
        // 3.4% комісія
        return amount.multiply(new BigDecimal("0.034"))
                     .setScale(2, RoundingMode.HALF_UP);
    }
    
        public boolean execute(PaymentRequest request, BigDecimal fee){
        System.out.println("[PayPal] Перенаправлення на шлюз PayPal для " + request.getPayerId() + ". Комісія системи: $" + fee);
        return true;
    }

}
