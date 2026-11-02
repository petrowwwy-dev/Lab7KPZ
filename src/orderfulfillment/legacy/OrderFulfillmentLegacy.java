package orderfulfillment.legacy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Вихідний «поганий» код (Variant 1, DIP) — збережено для порівняння. Не використовувати.
// Порушення DIP: OrderFulfillmentService напряму створює конкретні класи через new.
public class OrderFulfillmentLegacy {

    public static class Order {
        private String orderId;
        private String customerEmail;
        private double totalAmount;
        private List<String> items = new ArrayList<>();

        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }
        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public double getTotalAmount() { return totalAmount; }
        public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
        public List<String> getItems() { return items; }
        public void setItems(List<String> items) { this.items = items; }
    }

    public static class MySqlDatabaseRepository {
        public void saveOrder(Order order) {
            System.out.println("[MySQL DB] Замовлення " + order.getOrderId() + " на суму " + order.getTotalAmount() + " грн збережено в таблицю 'orders'.");
        }
    }

    public static class SmtpEmailService {
        public void sendOrderConfirmation(String email, String orderId) {
            System.out.println("[SMTP Email] Лист з підтвердженням замовлення " + orderId + " надіслано на " + email + ".");
        }
    }

    public static class FileAuditLogger {
        public void logTransaction(String message) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("[File Logger] Запис до файлу audit.log: " + time + " - " + message);
        }
    }

    public static class OrderFulfillmentService {
        private MySqlDatabaseRepository databaseRepository;
        private SmtpEmailService emailService;
        private FileAuditLogger fileLogger;

        public OrderFulfillmentService() {
            this.databaseRepository = new MySqlDatabaseRepository();
            this.emailService = new SmtpEmailService();
            this.fileLogger = new FileAuditLogger();
        }

        public void processOrder(Order order) {
            if (order == null || order.getItems().isEmpty()) {
                throw new IllegalArgumentException("Замовлення не може бути порожнім.");
            }

            this.databaseRepository.saveOrder(order);
            this.fileLogger.logTransaction("Успішно оброблено замовлення " + order.getOrderId());
            this.emailService.sendOrderConfirmation(order.getCustomerEmail(), order.getOrderId());
        }
    }
}
