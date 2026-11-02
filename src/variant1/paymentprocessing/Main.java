package variant1.paymentprocessing;

import java.math.BigDecimal;

import variant1.paymentprocessing.strategy.ApplePay;
import variant1.paymentprocessing.strategy.BankTransfer;
import variant1.paymentprocessing.strategy.CreditCard;
import variant1.paymentprocessing.strategy.Crypto;
import variant1.paymentprocessing.strategy.PayPal;
import variant1.paymentprocessing.strategy.PaymentMethod;
import variant1.paymentprocessing.strategy.PaymentRequest;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        PaymentRequest request = new PaymentRequest("TX-1001", new BigDecimal("250.00"), "USD", "user-42");

        PaymentMethod[] methods = {
            new CreditCard(),
            new PayPal(),
            new BankTransfer(),
            new Crypto(),
            new ApplePay()      // п'ятий спосіб
        };

        for (PaymentMethod method : methods) {
            processor.processPayment(request, method);
        }
    }
}