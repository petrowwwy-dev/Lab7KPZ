package orderfulfillment;

public class SmtpEmailService implements EmailService {
    @Override
    public void sendOrderConfirmation(String email, String orderId) {
        System.out.println("[SMTP Email] Лист з підтвердженням замовлення " + orderId
                + " надіслано на " + email + ".");
    }
}
