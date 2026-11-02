package lab7.variant1.orderfulfillment;

import java.util.ArrayList;
import java.util.List;

/**
 * Composition Root: єдине місце, де створюються конкретні реалізації та збирається граф залежностей.
 * Service Locator не використовується. Можна викликати з lab7.variant1.Main.
 */
public class OrderFulfillmentDemo {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println("=== Task 5: DIP (order fulfillment) ===");

        System.out.println("-- Production composition --");
        OrderFulfillmentService production = new OrderFulfillmentService(
                new MySqlOrderRepository(), new SmtpEmailService(), new FileAuditLogger());
        production.processOrder(sampleOrder());

        System.out.println("-- Test composition (підміна реалізацій) --");
        InMemoryOrderRepository repository = new InMemoryOrderRepository();
        List<String> sentEmails = new ArrayList<>();
        List<String> auditLog = new ArrayList<>();
        OrderFulfillmentService test = new OrderFulfillmentService(
                repository,
                (email, orderId) -> sentEmails.add(email + ":" + orderId), // EmailService — лямбда
                auditLog::add);                                            // AuditLogger — method reference
        test.processOrder(sampleOrder());
        System.out.println("saved=" + repository.getSavedOrders().size()
                + ", emails=" + sentEmails + ", audit=" + auditLog);

        System.out.println("-- Guard Clauses --");
        expectFailure("empty order", () -> test.processOrder(new Order()));
        expectFailure("null order", () -> test.processOrder(null));
        expectFailure("null dependency",
                () -> new OrderFulfillmentService(null, (e, i) -> { }, m -> { }));
        System.out.println();
    }

    private static Order sampleOrder() {
        Order order = new Order();
        order.setOrderId("ORD-1001");
        order.setCustomerEmail("customer@example.com");
        order.setTotalAmount(1499.90);
        order.setItems(List.of("Keyboard", "Mouse"));
        return order;
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
