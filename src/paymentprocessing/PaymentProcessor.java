package paymentprocessing;

import java.math.BigDecimal;

import paymentprocessing.strategy.PaymentMethod;
import paymentprocessing.strategy.PaymentRequest;

public class PaymentProcessor {

    public boolean processPayment(PaymentRequest request, PaymentMethod method) {
        System.out.println("[LOG] Початок обробки транзакції " + request.getTransactionId()
                + " на суму " + request.getAmount() + " " + request.getCurrency());

        BigDecimal fee = method.calculateProcessingFee(request.getAmount());
        return method.execute(request, fee);
    }
}