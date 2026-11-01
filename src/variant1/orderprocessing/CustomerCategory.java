package variant1.orderprocessing;

import java.math.BigDecimal;

public enum CustomerCategory {
    VIP("VIP", new BigDecimal("0.15")),
    REGULAR("Regular", new BigDecimal("0.05")),
    NONE("None", BigDecimal.ZERO);

    private final String label;
    private final BigDecimal discountRate;

    CustomerCategory(String label, BigDecimal discountRate) {
        this.label = label;
        this.discountRate = discountRate;
    }

    public String label() {
        return label;
    }

    public BigDecimal discountRate() {
        return discountRate;
    }

    /** Невідома або null категорія означає нуль знижки. */
    public static CustomerCategory fromString(String raw) {
        if ("VIP".equalsIgnoreCase(raw)) return VIP;
        if ("Regular".equalsIgnoreCase(raw)) return REGULAR;
        return NONE;
    }
}
