package lab7.variant1.bankingaccounts;

import java.math.BigDecimal;

/**
 * Строковий депозит: лише поповнення. НЕ реалізує {@link WithdrawableAccount},
 * тому передати його туди, де потрібне зняття, неможливо ще на етапі компіляції.
 *
 * <p>Використовує композицію замість успадкування від BankAccount: валідація та облік
 * балансу делегуються внутрішньому рахунку (DRY), а метод withdraw назовні не відкривається.
 */
public class FixedDepositAccount implements Account {
    private final BankAccount ledger;
    private final int termMonths;

    public FixedDepositAccount(String accountNumber, BigDecimal initialBalance, int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("termMonths must be positive");
        }
        this.ledger = new BankAccount(accountNumber, initialBalance);
        this.termMonths = termMonths;
    }

    public int getTermMonths() { return termMonths; }

    @Override
    public String getAccountNumber() { return ledger.getAccountNumber(); }

    @Override
    public BigDecimal getBalance() { return ledger.getBalance(); }

    @Override
    public void deposit(BigDecimal amount) { ledger.deposit(amount); }
}
