package lab7.variant1.orderfulfillment;

/** Абстракція сховища замовлень (власність високорівневого модуля). */
public interface OrderRepository {
    void saveOrder(Order order);
}
