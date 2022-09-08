public class Product {
    private String name;
    private ProductType type;
    private int price;              // cents
    private int preparationTime;    // seconds
    private int amountLeft;         // cup
    
    public Product(String name, ProductType type, int price, int preparationTime, int amountLeft) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.preparationTime = preparationTime;
        this.amountLeft = amountLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public void decrementAmountLeft() {
        this.amountLeft--;
    }
}
