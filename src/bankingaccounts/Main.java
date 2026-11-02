package bankingaccounts;

import bankingaccounts.legacy.BankAccountLegacy;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        demoLsp();
    }

    private static void demoLsp() {
        System.out.println("=== Task 3: LSP (banking accounts) ===");

        System.out.println("-- Legacy: підстановка ламає клієнта --");
        BankAccountLegacy.BankAccount legacyDeposit =
                new BankAccountLegacy.FixedDepositAccount("UA-L", new BigDecimal("5000"), 12);
        try {
            new BankAccountLegacy.BankService().processMonthlyFee(legacyDeposit, new BigDecimal("50"));
        } catch (UnsupportedOperationException e) {
            System.out.println("[LEGACY BUG] " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("-- Refactored --");
        BankService service = new BankService();
        BankAccount current = new BankAccount("UA01", new BigDecimal("1000"));
        FixedDepositAccount deposit = new FixedDepositAccount("UA02", new BigDecimal("5000"), 12);

        service.processMonthlyFee(current, new BigDecimal("50"));

        for (Account a : List.<Account>of(current, deposit)) {
            a.deposit(new BigDecimal("100"));
            System.out.println(a.getAccountNumber() + " -> " + a.getBalance());
        }

        System.out.println("-- Guard Clauses / інваріанти --");
        expectFailure("zero deposit", () -> current.deposit(BigDecimal.ZERO));
        expectFailure("null amount", () -> current.deposit(null));
        expectFailure("overdraft", () -> current.withdraw(new BigDecimal("999999")));
        expectFailure("blank number", () -> new BankAccount(" ", BigDecimal.ZERO));
        expectFailure("negative initial balance", () -> new BankAccount("UA03", new BigDecimal("-1")));
        expectFailure("term <= 0", () -> new FixedDepositAccount("UA04", BigDecimal.ZERO, 0));
        System.out.println();
    }

    private static void expectFailure(String name, Runnable action) {
        try {
            action.run();
            System.out.println("[FAIL] " + name + ": виняток не кинуто");
        } catch (RuntimeException e) {
            System.out.println("[OK]   " + name + " -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
