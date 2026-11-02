package lab7.variant1.bankingaccounts;

import java.math.BigDecimal;

/**
 * Базовий контракт, спільний для УСІХ рахунків (мінімум, який гарантує кожен нащадок).
 *
 * <p>deposit: передумова amount != null && amount > 0 (інакше IllegalArgumentException / NPE);
 * постумова: баланс збільшився рівно на amount.
 */
public interface Account {
    String getAccountNumber();

    BigDecimal getBalance();

    void deposit(BigDecimal amount);
}
