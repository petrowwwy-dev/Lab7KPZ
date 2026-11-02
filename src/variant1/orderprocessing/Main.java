package variant1.orderprocessing;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderProcessingService service = new OrderProcessingService(
                new OrderValidator(),
                new OrderPriceCalculator(),
                new InvoiceHtmlFormatter(),
                new OrderFileRepository("orders_db.txt"),
                new EmailNotificationService());

        Order order = new Order(
                "client@example.com",
                CustomerCategory.fromString("VIP"),
                List.of(
                        new OrderItem("A-1", "Ноутбук", new BigDecimal("32000.00"), 1),
                        new OrderItem("B-7", "Мишка", new BigDecimal("599.50"), 2)),
                "Чернівці, вул. Університетська, 1");

        service.process(order);
    }
}
