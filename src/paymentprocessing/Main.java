package paymentprocessing;

import java.math.BigDecimal;

import paymentprocessing.strategy.ApplePay;
import paymentprocessing.strategy.BankTransfer;
import paymentprocessing.strategy.CreditCard;
import paymentprocessing.strategy.Crypto;
import paymentprocessing.strategy.PayPal;
import paymentprocessing.strategy.PaymentMethod;
import paymentprocessing.strategy.PaymentRequest;

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