package orderfulfillment;

/** Абстракція сховища замовлень (власність високорівневого модуля). */
public interface OrderRepository {
    void saveOrder(Order order);
}
