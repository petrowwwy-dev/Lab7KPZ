package variant1.orderprocessing.legacy;

    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.math.BigDecimal;
    import java.math.RoundingMode;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

    // Опис предметної області: Інтернет-магазин електроніки та побутової техніки.
    // Завдання коду: Обробка замовлення клієнта — 
    // 1)перевірка коректності даних, 
    // 2)розрахунок загальної вартості з урахуванням накопичувальної знижки та ПДВ,
    // 3)формування HTML-рахунку,
    // 4)збереження замовлення у текстову базу даних та 
    // 5)відправка email-підтвердження.

    public class OrderItem {
        private String itemId;
        private String name;
        private BigDecimal price;
        private int quantity;

        public String getItemId() { return itemId; }
        public void setItemId(String itemId) { this.itemId = itemId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }