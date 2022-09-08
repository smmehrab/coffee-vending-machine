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

    public void decrementCountBy(String type, int decrementCount) {
        switch(type) {
            case "10 Cents":
                this.tenCentsCount -= decrementCount;
                break;
            case "20 Cents":
                this.twentyCentsCount -= decrementCount;
                break;
            case "50 Cents":
                this.fiftyCentsCount -= decrementCount;
                break;
            default:
                break;
        }
    }

    public static MoneyReturn calculateReturn(Money payment, int price, Money machinMoney) {
        boolean status = false;
        Money money = new Money();

        String type;
        int returnMoneyAmount = payment.getAmount()-price;
        int coinOrNote;
        int coinOrNoteCount;
        int availableCoinOrNoteCount;
        int quotient=0;
        int remainder=returnMoneyAmount;

        for (int i = types.size()-1; i >= 0; i--) {
            type = types.get(i);
            coinOrNote = Integer.parseInt(type.split(" ")[0]);
            coinOrNoteCount = 0;

            System.out.println(coinOrNote + " " + remainder);

            do {
                if(remainder<coinOrNote) {
                    break;
                }

                quotient = remainder/coinOrNote;
                availableCoinOrNoteCount = machinMoney.getCount(type);
                if(availableCoinOrNoteCount>=(coinOrNoteCount+quotient)) {
                    remainder %= coinOrNote;
                    coinOrNoteCount += quotient;
                    machinMoney.decrementCountBy(type, quotient);
                }
                else {
                    remainder = remainder - (coinOrNote*availableCoinOrNoteCount);
                    coinOrNoteCount += availableCoinOrNoteCount;
                    machinMoney.decrementCountBy(type, availableCoinOrNoteCount);
                    break;
                }

            } while(quotient>0);

            money.add(type, coinOrNoteCount);
        }

        if(remainder==0) {
            status = true;
        }
        else {
            money = new Money();
        }

        return new MoneyReturn(status, money);
    }
}
