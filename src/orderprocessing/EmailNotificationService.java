package orderprocessing;

public class EmailNotificationService {

    public void sendOrderConfirmation(String to, String htmlBody) {
        System.out.println("Надсилання email на " + to + " через smtp.store.local:587");
        System.out.println("Тема: Ваше замовлення успішно оформлено");
        System.out.println("Тіло: " + htmlBody);
    }
}
