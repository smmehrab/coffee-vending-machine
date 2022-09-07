import java.util.ArrayList;
import java.util.List;

public class Money {
    private int tenCentsCoins;
    private int twentyCentsCoins;
    private int fiftyCentsCoins;
    private static ArrayList<String> types = new ArrayList<>(List.of("10 Cents", "20 Cents", "30 Cents"));

    Money() {}

    Money(int tenCentsCoins, int twentyCentsCoins, int fiftyCentsCoins) {
        this.tenCentsCoins = tenCentsCoins;
        this.twentyCentsCoins = twentyCentsCoins;
        this.fiftyCentsCoins = fiftyCentsCoins;
    }

    public int getTenCentsCoins() {
        return tenCentsCoins;
    }

    public void setTenCentsCoins(int count) {
        this.tenCentsCoins = count;
    }

    public void addTenCentsCoins(int count) {
        this.tenCentsCoins += count;
    }

    public int getTwentyCentsCoins() {
        return twentyCentsCoins;
    }

    public void setTwentyCentsCoins(int count) {
        this.twentyCentsCoins = count;
    }

    public void addTwentyCentsCoins(int count) {
        this.twentyCentsCoins += count;
    }

    public int getFiftyCentsCoins() {
        return fiftyCentsCoins;
    }

    public void setFiftyCentsCoins(int count) {
        this.fiftyCentsCoins = count;
    }

    public void addFiftyCentsCoins(int count) {
        this.fiftyCentsCoins += count;
    }

    public int getAmount() {
        int amount = 0;
        amount += (tenCentsCoins*10);
        amount += (twentyCentsCoins*20);
        amount += (fiftyCentsCoins*50);
        return amount;
    }

    public static ArrayList<String> getTypes() {
        return types;
    }
}
