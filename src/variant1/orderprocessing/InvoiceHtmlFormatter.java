package variant1.orderprocessing;

import java.math.RoundingMode;

public class InvoiceHtmlFormatter {

    public String format(Order order, OrderTotals totals) {
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>Рахунок на замовлення для ")
                .append(order.customerEmail()).append("</h1>");
        html.append("<p>Адреса доставки: ")
                .append(order.deliveryAddress()).append("</p>");
        html.append("<table border='1'><tr><th>Товар</th><th>Кількість</th><th>Ціна</th></tr>");

        for (OrderItem item : order.items()) {
            html.append("<tr><td>").append(item.name())
                    .append("</td><td>").append(item.quantity())
                    .append("</td><td>").append(item.price()).append(" грн</td></tr>");
        }

        html.append("</table>");
        html.append("<p>Проміжна сума: ").append(totals.subtotal()).append(" грн</p>");
        html.append("<p>Знижка (").append(order.category().label()).append("): -")
                .append(totals.discountAmount()).append(" грн</p>");
        html.append("<p>ПДВ (").append(vatPercent(totals)).append("%): ")
            .append(totals.taxAmount()).append(" грн</p>");
        html.append("<h3>Загалом до сплати: ")
                .append(totals.total().setScale(2, RoundingMode.HALF_UP))
                .append(" грн</h3>");
        html.append("</body></html>");

        return html.toString();
    }

    private String vatPercent(OrderTotals totals) {
        return totals.vatRate().movePointRight(2).stripTrailingZeros().toPlainString();
    }
}
