public class Product {
    private String name;
    private ProductType type;
    private int centsPrice;
    private int cupQuantity;
    private int secondsPreparationTime;

    public Product(String name, ProductType type, int centsPrice, int cupQuantity, int secondsPreparationTime) {
        this.name = name;
        this.type = type;
        this.centsPrice = centsPrice;
        this.cupQuantity = cupQuantity;
        this.secondsPreparationTime = secondsPreparationTime;
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

    public int getCentsPrice() {
        return centsPrice;
    }

    public void setCentsPrice(int centsPrice) {
        this.centsPrice = centsPrice;
    }

    public int getCupQuantity() {
        return cupQuantity;
    }

    public void setCupQuantity(int cupQuantity) {
        this.cupQuantity = cupQuantity;
    }

    public int getSecondsPreparationTime() {
        return secondsPreparationTime;
    }

    public void setSecondsPreparationTime(int secondsPreparationTime) {
        this.secondsPreparationTime = secondsPreparationTime;
    }
    
}
