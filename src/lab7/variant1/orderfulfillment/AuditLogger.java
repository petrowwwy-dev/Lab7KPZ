package lab7.variant1.orderfulfillment;

/** Абстракція аудит-логу транзакцій. */
public interface AuditLogger {
    void logTransaction(String message);
}
