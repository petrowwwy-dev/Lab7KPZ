package lab7.variant1;

import lab7.variant1.bankingaccounts.Account;
import lab7.variant1.bankingaccounts.BankAccount;
import lab7.variant1.bankingaccounts.BankService;
import lab7.variant1.bankingaccounts.FixedDepositAccount;
import lab7.variant1.bankingaccounts.legacy.BankAccountLegacy;
import lab7.variant1.orderfulfillment.OrderFulfillmentDemo;
import lab7.variant1.smarthomedevices.CoffeeBrewer;
import lab7.variant1.smarthomedevices.Dimmable;
import lab7.variant1.smarthomedevices.SmartCoffeeMaker;
import lab7.variant1.smarthomedevices.SmartLight;
import lab7.variant1.smarthomedevices.SmartThermostat;
import lab7.variant1.smarthomedevices.Switchable;
import lab7.variant1.smarthomedevices.TemperatureControllable;
import lab7.variant1.smarthomedevices.legacy.SmartDeviceLegacy;

import java.math.BigDecimal;
import java.util.List;

/** Демонстраційна точка входу для Лабораторної №7, варіант 1. */
public class Main {

    public static void main(String[] args) {
        demoLsp();
        demoIsp();
        OrderFulfillmentDemo.run(); // Task 5: DIP (Composition Root)
        // TODO: demoSrp(), demoOcp() — після додавання відповідних пакетів
    }

    // ---------- Завдання 3: LSP ----------
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
        // service.processMonthlyFee(deposit, new BigDecimal("50")); // ПОМИЛКА КОМПІЛЯЦІЇ — fail-fast

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

    // ---------- Завдання 4: ISP ----------
    private static void demoIsp() {
        System.out.println("=== Task 4: ISP (smart home devices) ===");

        System.out.println("-- Legacy: «жирний» інтерфейс --");
        SmartDeviceLegacy.ISmartDevice legacyLight = new SmartDeviceLegacy.SmartLight();
        try {
            legacyLight.brewCoffee();
        } catch (UnsupportedOperationException e) {
            System.out.println("[LEGACY BUG] " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("-- Refactored --");
        SmartThermostat thermostat = new SmartThermostat();
        SmartLight light = new SmartLight();
        SmartCoffeeMaker coffeeMaker = new SmartCoffeeMaker();

        // Кожен клієнт приймає лише ту роль, методи якої викликає
        turnOffAll(List.of(thermostat, light, coffeeMaker));
        heat(thermostat, 22.5);
        dim(light, 40);
        morningCoffee(coffeeMaker);

        // heat(light, 22.5);      // ПОМИЛКА КОМПІЛЯЦІЇ — світло не TemperatureControllable
        // morningCoffee(thermostat); // ПОМИЛКА КОМПІЛЯЦІЇ — термостат не CoffeeBrewer
        System.out.println();
    }

    /** Клієнт «вимкнути все»: потрібен лише Switchable. */
    private static void turnOffAll(List<? extends Switchable> devices) {
        devices.forEach(Switchable::powerOff);
    }

    /** Клієнт «опалення»: потрібен лише TemperatureControllable. */
    private static void heat(TemperatureControllable device, double temp) {
        device.setTemperature(temp);
    }

    /** Клієнт «освітлення»: потрібен лише Dimmable. */
    private static void dim(Dimmable device, int level) {
        device.setBrightness(level);
    }

    /** Клієнт «ранкова кава»: потрібен лише CoffeeBrewer. */
    private static void morningCoffee(CoffeeBrewer device) {
        device.brewCoffee();
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
