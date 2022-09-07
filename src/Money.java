import java.util.ArrayList;
import java.util.List;

public class Money {
    private int tenCentsCount;
    private int twentyCentsCount;
    private int fiftyCentsCount;
    private static ArrayList<String> types = new ArrayList<>(List.of("10 Cents", "20 Cents", "50 Cents"));

    Money() {
        this.reset();
    }

    Money(int tenCentsCount, int twentyCentsCount, int fiftyCentsCount) {
        this.tenCentsCount = tenCentsCount;
        this.twentyCentsCount = twentyCentsCount;
        this.fiftyCentsCount = fiftyCentsCount;
    }

    public static ArrayList<String> getTypes() {
        return types;
    }

    public void add(String type, int numberOfCoinsOrNotes) {
        switch(type) {
            case "10 Cents":
                this.tenCentsCount += numberOfCoinsOrNotes;
                break;
            case "20 Cents":
                this.twentyCentsCount += numberOfCoinsOrNotes;
                break;
            case "50 Cents":
                this.fiftyCentsCount += numberOfCoinsOrNotes;
                break;
            default:
                break;
        }
    }

    public int getCount(String type) {
        switch(type) {
            case "10 Cents":
                return this.tenCentsCount;
            case "20 Cents":
                return this.twentyCentsCount;
            case "50 Cents":
                return this.fiftyCentsCount;
            default:
                break;
        }
        return 0;
    }

    public int getAmount() {
        int amount = 0;
        amount += (tenCentsCount*10);
        amount += (twentyCentsCount*20);
        amount += (fiftyCentsCount*50);
        return amount;
    }

    public void reset() {
        this.tenCentsCount = 0;
        this.twentyCentsCount = 0;
        this.fiftyCentsCount = 0;
    }
}
