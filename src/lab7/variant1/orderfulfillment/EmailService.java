package lab7.variant1.orderfulfillment;

/** Абстракція сповіщення покупця. */
public interface EmailService {
    void sendOrderConfirmation(String email, String orderId);
}
