package mate.academy.internetshop.model;

public class Item {
    private Long id;
    private String name;
    private Double price;
    private int count;

    public Item(String itemName, Double itemPrice, int initialCount) {
        name = itemName;
        price = itemPrice;
        count = initialCount;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", count=" + count
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
