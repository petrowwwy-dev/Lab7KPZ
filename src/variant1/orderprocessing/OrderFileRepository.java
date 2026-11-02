package variant1.orderprocessing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFileRepository {

    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String filePath;

    public OrderFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public void save(Order order, OrderTotals totals) {
        String record = String.format("%s|%s|%s|%s%n",
                LocalDateTime.now().format(TIMESTAMP),
                order.customerEmail(),
                totals.total(),
                order.deliveryAddress());

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.print(record);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл бази даних: " + e.getMessage());
        }
    }
}
