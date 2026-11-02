package paymentprocessing.strategy;

import java.math.BigDecimal;

public class BankTransfer implements PaymentMethod {

        public BigDecimal calculateProcessingFee(BigDecimal amount) {
                // Фіксована комісія $5.00 незалежно від суми
                return new BigDecimal("5.00");
        }

        public boolean execute(PaymentRequest request, BigDecimal fee) {
                System.out.println("[BankTransfer] Формування платіжного доручення SWIFT/IBAN для "
                                + request.getPayerId() + ". Сума: $" + request.getAmount().add(fee));
                return true;
        }

}
