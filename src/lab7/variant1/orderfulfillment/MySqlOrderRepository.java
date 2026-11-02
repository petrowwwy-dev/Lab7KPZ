package lab7.variant1.orderfulfillment;

public class MySqlOrderRepository implements OrderRepository {
    @Override
    public void saveOrder(Order order) {
        System.out.println("[MySQL DB] Замовлення " + order.getOrderId() + " на суму "
                + order.getTotalAmount() + " грн збережено в таблицю 'orders'.");
    }
}
