package variant1.orderprocessing;

import java.math.BigDecimal;

public class OrderValidator {

    public void validate(Order order) {
        String email = order.customerEmail();
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Некоректна електронна адреса покупця.");
        }

        if (order.items() == null || order.items().isEmpty()) {
            throw new IllegalStateException("Кошик порожній.");
        }

        for (OrderItem item : order.items()) {
            if (item.quantity() <= 0
                    || item.price() == null
                    || item.price().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException(
                        "Некоректний товар " + item.name()
                                + ": ціна та кількість мають бути більше 0.");
            }
        }
    }
}
