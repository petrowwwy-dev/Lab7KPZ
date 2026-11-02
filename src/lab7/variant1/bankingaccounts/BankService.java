package lab7.variant1.bankingaccounts;

import java.math.BigDecimal;

public class BankService {

    /** Залежить лише від ролі WithdrawableAccount — саме її метод викликає. */
    public void processMonthlyFee(WithdrawableAccount account, BigDecimal fee) {
        account.withdraw(fee);
        System.out.println("Fee processed for " + account.getAccountNumber()
                + ". New balance: " + account.getBalance());
    }
}
