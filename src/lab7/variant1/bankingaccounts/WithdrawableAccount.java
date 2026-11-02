package lab7.variant1.bankingaccounts;

import java.math.BigDecimal;

/**
 * Роль: рахунок, з якого можна знімати кошти.
 *
 * <p>Передумова: 0 < amount <= balance (IllegalArgumentException / IllegalStateException).
 * Постумова: баланс зменшився рівно на amount.
 */
public interface WithdrawableAccount extends Account {
    void withdraw(BigDecimal amount);
}
