package bankingaccounts;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Звичайний (поточний) рахунок: підтримує і поповнення, і зняття.
 * Інваріанти: номер не порожній, баланс не null і ніколи не від'ємний.
 */
public class BankAccount implements WithdrawableAccount {
    private final String accountNumber;
    private BigDecimal balance;

    public BankAccount(String accountNumber, BigDecimal initialBalance) {
        Objects.requireNonNull(accountNumber, "accountNumber must not be null");
        Objects.requireNonNull(initialBalance, "initialBalance must not be null");
        if (accountNumber.isBlank()) {
            throw new IllegalArgumentException("accountNumber must not be blank");
        }
        if (initialBalance.signum() < 0) {
            throw new IllegalArgumentException("initialBalance must not be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    @Override
    public String getAccountNumber() { return accountNumber; }

    @Override
    public BigDecimal getBalance() { return balance; }

    @Override
    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        requirePositive(amount);
        if (amount.compareTo(balance) > 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance.subtract(amount);
    }

    private static void requirePositive(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
