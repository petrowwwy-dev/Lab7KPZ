package variant1.paymentprocessing.strategy;

import java.math.BigDecimal;

public class PaymentRequest {
    private String transactionId;
    private BigDecimal amount;
    private String currency;
    private String payerId;

    public PaymentRequest(String transactionId, BigDecimal amount, String currency, String payerId){
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
