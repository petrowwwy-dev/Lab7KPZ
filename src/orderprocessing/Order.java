package orderprocessing;

import java.util.List;

public record Order(
        String customerEmail,
        CustomerCategory category,
        List<OrderItem> items,
        String deliveryAddress) {
}
