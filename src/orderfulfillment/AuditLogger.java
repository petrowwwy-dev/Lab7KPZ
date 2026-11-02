package orderfulfillment;

/** Абстракція аудит-логу транзакцій. */
public interface AuditLogger {
    void logTransaction(String message);
}
