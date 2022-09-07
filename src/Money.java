import java.util.ArrayList;
import java.util.List;

public class Money {
    private int tenCentsCount;
    private int twentyCentsCount;
    private int fiftyCentsCount;
    private static ArrayList<String> types = new ArrayList<>(List.of("10 Cents", "20 Cents", "30 Cents"));

    Money() {}

    Money(int tenCentsCount, int twentyCentsCount, int fiftyCentsCount) {
        this.tenCentsCount = tenCentsCount;
        this.twentyCentsCount = twentyCentsCount;
        this.fiftyCentsCount = fiftyCentsCount;
    }

    public int getTenCentsCount() {
        return tenCentsCount;
    }

    public void setTenCentsCount(int count) {
        this.tenCentsCount = count;
    }

    public void addTenCentsCount(int count) {
        this.tenCentsCount += count;
    }

    public int getTwentyCentsCount() {
        return twentyCentsCount;
    }

    public void setTwentyCentsCount(int count) {
        this.twentyCentsCount = count;
    }

    public void addTwentyCentsCount(int count) {
        this.twentyCentsCount += count;
    }

    public int getFiftyCentsCount() {
        return fiftyCentsCount;
    }

    public void setFiftyCentsCount(int count) {
        this.fiftyCentsCount = count;
    }

    public void addFiftyCentsCount(int count) {
        this.fiftyCentsCount += count;
    }

    public int getAmount() {
        int amount = 0;
        amount += (tenCentsCount*10);
        amount += (twentyCentsCount*20);
        amount += (fiftyCentsCount*50);
        return amount;
    }

    public static ArrayList<String> getTypes() {
        return types;
    }
}
