package orderfulfillment;

import java.util.Objects;

/**
 * Високорівнева бізнес-логіка. Залежить ТІЛЬКИ від абстракцій;
 * конкретні реалізації надходять через конструктор (Constructor Injection).
 */
public class OrderFulfillmentService {
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final AuditLogger auditLogger;

    public OrderFulfillmentService(OrderRepository orderRepository,
                                   EmailService emailService,
                                   AuditLogger auditLogger) {
        this.orderRepository = Objects.requireNonNull(orderRepository, "orderRepository must not be null");
        this.emailService = Objects.requireNonNull(emailService, "emailService must not be null");
        this.auditLogger = Objects.requireNonNull(auditLogger, "auditLogger must not be null");
    }

    public void processOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Замовлення не може бути порожнім.");
        }

        orderRepository.saveOrder(order);
        auditLogger.logTransaction("Успішно оброблено замовлення " + order.getOrderId());
        emailService.sendOrderConfirmation(order.getCustomerEmail(), order.getOrderId());
    }
}
