package lab7.variant1.orderfulfillment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileAuditLogger implements AuditLogger {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void logTransaction(String message) {
        String time = LocalDateTime.now().format(FORMAT);
        System.out.println("[File Logger] Запис до файлу audit.log: " + time + " - " + message);
    }
}
