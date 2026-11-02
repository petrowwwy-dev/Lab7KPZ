package orderfulfillment;

/** Абстракція сповіщення покупця. */
public interface EmailService {
    void sendOrderConfirmation(String email, String orderId);
}
