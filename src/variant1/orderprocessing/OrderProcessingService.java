package variant1.orderprocessing;

public class OrderProcessingService {

    private final OrderValidator validator;
    private final OrderPriceCalculator calculator;
    private final InvoiceHtmlFormatter invoiceFormatter;
    private final OrderFileRepository repository;
    private final EmailNotificationService notifier;

    public OrderProcessingService(
            OrderValidator validator,
            OrderPriceCalculator calculator,
            InvoiceHtmlFormatter invoiceFormatter,
            OrderFileRepository repository,
            EmailNotificationService notifier) {
        this.validator = validator;
        this.calculator = calculator;
        this.invoiceFormatter = invoiceFormatter;
        this.repository = repository;
        this.notifier = notifier;
    }

    public void process(Order order) {
        validator.validate(order);
        OrderTotals totals = calculator.calculate(order);
        String invoice = invoiceFormatter.format(order, totals);
        repository.save(order, totals);
        notifier.sendOrderConfirmation(order.customerEmail(), invoice);
    }
}
