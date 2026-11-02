package bankingaccounts.legacy;

import java.math.BigDecimal;

// Вихідний «поганий» код (Variant 1, LSP) — збережено для порівняння. Не використовувати.
// Порушення LSP: FixedDepositAccount.withdraw() кидає UnsupportedOperationException,
// хоча клієнт (BankService) очікує, що withdraw працює для будь-якого BankAccount.
public class BankAccountLegacy {

    public static class BankAccount {
        private String accountNumber;
        private BigDecimal balance;

        public BankAccount(String accountNumber, BigDecimal initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        public String getAccountNumber() { return accountNumber; }
        public BigDecimal getBalance() { return balance; }

        public void deposit(BigDecimal amount) {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
            balance = balance.add(amount);
        }

        public void withdraw(BigDecimal amount) {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
            if (amount.compareTo(balance) > 0) throw new IllegalStateException("Insufficient funds");
            balance = balance.subtract(amount);
        }
    }

    public static class FixedDepositAccount extends BankAccount {
        private int termMonths;

        public FixedDepositAccount(String accountNumber, BigDecimal initialBalance, int termMonths) {
            super(accountNumber, initialBalance);
            this.termMonths = termMonths;
        }

        public int getTermMonths() { return termMonths; }

        @Override
        public void withdraw(BigDecimal amount) {
            // Порушення LSP: Спадкоємець відмовляється виконувати базовий контракт withdraw
            throw new UnsupportedOperationException("Withdrawal is not allowed for Fixed Deposit accounts before maturity date!");
        }
    }

    public static class BankService {
        public void processMonthlyFee(BankAccount account, BigDecimal fee) {
            // Клієнтський код розраховує на підтримку withdraw для будь-якого BankAccount
            account.withdraw(fee);
            System.out.println("Fee processed for " + account.getAccountNumber() + ". New balance: " + account.getBalance());
        }
    }
}
