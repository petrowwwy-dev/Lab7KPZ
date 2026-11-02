package paymentprocessing.strategy;

import java.math.BigDecimal;

public interface PaymentMethod {

    BigDecimal calculateProcessingFee(BigDecimal amount);

    boolean execute(PaymentRequest request, BigDecimal fee);
}
