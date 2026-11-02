package lab7.variant1.orderfulfillment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Підміна для тестів/демо: зберігає замовлення в пам'яті, без БД. */
public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> saved = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        saved.add(Objects.requireNonNull(order, "order must not be null"));
    }

    public List<Order> getSavedOrders() {
        return Collections.unmodifiableList(saved);
    }
}
