package orderprocessing.legacy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Опис предметної області: Інтернет-магазин електроніки та побутової техніки.
// Завдання коду: Обробка замовлення клієнта — перевірка коректності даних, розрахунок загальної вартості з урахуванням накопичувальної знижки та ПДВ, формування HTML-рахунку, збереження замовлення у текстову базу даних та відправка email-підтвердження.


public class OrderProcessor {
    public void processOrder(String customerEmail, String customerCategory, List<OrderItem> items, String deliveryAddress) {
        // 1. Валідація замовлення
        if (customerEmail == null || customerEmail.isBlank() || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("Некоректна електронна адреса покупця.");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("Кошик порожній.");
        }

        for (OrderItem item : items) {
            if (item.getQuantity() <= 0 || item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Некоректний товар " + item.getName() + ": ціна та кількість мають бути більше 0.");
            }
        }

        // 2. Розрахунок вартості, знижок та податків
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItem item : items) {
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            subtotal = subtotal.add(itemTotal);
        }

        BigDecimal discountRate = BigDecimal.ZERO;
        if ("VIP".equalsIgnoreCase(customerCategory)) {
            discountRate = new BigDecimal("0.15");
        } else if ("Regular".equalsIgnoreCase(customerCategory)) {
            discountRate = new BigDecimal("0.05");
        }

        BigDecimal discountAmount = subtotal.multiply(discountRate);
        BigDecimal taxableSubtotal = subtotal.subtract(discountAmount);
        BigDecimal taxAmount = taxableSubtotal.multiply(new BigDecimal("0.20")); // ПДВ 20%
        BigDecimal totalAmount = taxableSubtotal.add(taxAmount);

        // 3. Формування HTML-рахунку
        StringBuilder htmlInvoice = new StringBuilder("<html><body>");
        htmlInvoice.append("<h1>Рахунок на замовлення для ").append(customerEmail).append("</h1>");
        htmlInvoice.append("<p>Адреса доставки: ").append(deliveryAddress).append("</p>");
        htmlInvoice.append("<table border='1'><tr><th>Товар</th><th>Кількість</th><th>Ціна</th></tr>");

        for (OrderItem item : items) {
            htmlInvoice.append("<tr><td>").append(item.getName())
                       .append("</td><td>").append(item.getQuantity())
                       .append("</td><td>").append(item.getPrice()).append(" грн</td></tr>");
        }

        htmlInvoice.append("</table>");
        htmlInvoice.append("<p>Проміжна сума: ").append(subtotal).append(" грн</p>");
        htmlInvoice.append("<p>Знижка (").append(customerCategory).append("): -").append(discountAmount).append(" грн</p>");
        htmlInvoice.append("<p>ПДВ (20%): ").append(taxAmount).append(" грн</p>");
        htmlInvoice.append("<h3>Загалом до сплати: ").append(totalAmount.setScale(2, RoundingMode.HALF_UP)).append(" грн</h3>");
        htmlInvoice.append("</body></html>");

        // 4. Збереження у локальну базу даних (файл)
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String record = String.format("%s|%s|%s|%s%n", timestamp, customerEmail, totalAmount, deliveryAddress);
        try (FileWriter fw = new FileWriter("orders_db.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print(record);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл бази даних: " + e.getMessage());
        }

        // 5. Відправка email сповіщення
        try {
            // Симуляція відправки email через SMTP
            System.out.println("Надсилання email на " + customerEmail + " через smtp.store.local:587");
            System.out.println("Тема: Ваше замовлення успішно оформлено");
            System.out.println("Тіло: " + htmlInvoice.toString());
        } catch (Exception ex) {
            System.out.println("Помилка відправки email: " + ex.getMessage());
        }
    }
}