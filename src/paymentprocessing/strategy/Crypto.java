package paymentprocessing.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Crypto implements PaymentMethod {
    public BigDecimal calculateProcessingFee(BigDecimal amount) {
        // 0.8% комісія, але не менше $1.00 і не більше $20.00
        BigDecimal fee = amount.multiply(new BigDecimal("0.008")).setScale(2, RoundingMode.HALF_UP);
        if (fee.compareTo(new BigDecimal("1.00")) < 0) return new BigDecimal("1.00");
        if (fee.compareTo(new BigDecimal("20.00")) > 0) return new BigDecimal("20.00");
        return fee;
    }

    public boolean execute(PaymentRequest request, BigDecimal fee){
        System.out.println("[Crypto] Очікування підтвердження транзакції в мережі Blockchain для " + request.getPayerId() + ".");
        return true;
    }
}
