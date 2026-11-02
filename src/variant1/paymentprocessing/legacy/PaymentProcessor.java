package variant1.paymentprocessing.legacy;
    import java.math.BigDecimal;
    import java.math.RoundingMode;

    // Опис предметної області: Платіжний шлюз для інтернет-еквайрингу.
    // Завдання коду: Розрахунок комісії та проведення платежу залежно від обраного типу платіжної системи.
    // 
    // 

    enum PaymentType {
        CREDIT_CARD,
        PAYPAL,
        BANK_TRANSFER,
        CRYPTO
    }

    class PaymentRequest {
        private String transactionId;
        private BigDecimal amount;
        private String currency;
        private String payerId;

        public PaymentRequest(String transactionId, BigDecimal amount, String currency, String payerId) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.currency = currency;
            this.payerId = payerId;
        }

        public String getTransactionId() { return transactionId; }
        public BigDecimal getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getPayerId() { return payerId; }
    }

    public class PaymentProcessor {

        public BigDecimal calculateProcessingFee(BigDecimal amount, PaymentType type) {
            switch (type) {
                case CREDIT_CARD:
                    // 2.5% + $0.30 фіксована комісія
                    return amount.multiply(new BigDecimal("0.025"))
                                 .add(new BigDecimal("0.30"))
                                 .setScale(2, RoundingMode.HALF_UP);

                case PAYPAL:
                    // 3.4% комісія
                    return amount.multiply(new BigDecimal("0.034"))
                                 .setScale(2, RoundingMode.HALF_UP);

                case BANK_TRANSFER:
                    // Фіксована комісія $5.00 незалежно від суми
                    return new BigDecimal("5.00");

                case CRYPTO:
                    // 0.8% комісія, але не менше $1.00 і не більше $20.00
                    BigDecimal fee = amount.multiply(new BigDecimal("0.008")).setScale(2, RoundingMode.HALF_UP);
                    if (fee.compareTo(new BigDecimal("1.00")) < 0) return new BigDecimal("1.00");
                    if (fee.compareTo(new BigDecimal("20.00")) > 0) return new BigDecimal("20.00");
                    return fee;

                default:
                    throw new IllegalArgumentException("Непідтримуваний тип платежу: " + type);
            }
        }

        public boolean processPayment(PaymentRequest request, PaymentType type) {
            System.out.println("[LOG] Початок обробки транзакції " + request.getTransactionId() + " на суму " + request.getAmount() + " " + request.getCurrency());

            BigDecimal fee = calculateProcessingFee(request.getAmount(), type);
            BigDecimal totalAmount = request.getAmount().add(fee);

            switch (type) {
                case CREDIT_CARD:
                    System.out.println("[CreditCard] Авторизація картки для користувача " + request.getPayerId() + ". Списано: $" + totalAmount);
                    return true;

                case PAYPAL:
                    System.out.println("[PayPal] Перенаправлення на шлюз PayPal для " + request.getPayerId() + ". Комісія системи: $" + fee);
                    return true;

                case BANK_TRANSFER:
                    System.out.println("[BankTransfer] Формування платіжного доручення SWIFT/IBAN для " + request.getPayerId() + ". Сума: $" + totalAmount);
                    return true;

                case CRYPTO:
                    System.out.println("[Crypto] Очікування підтвердження транзакції в мережі Blockchain для " + request.getPayerId() + ".");
                    return true;

                default:
                    return false;
            }
        }
    }