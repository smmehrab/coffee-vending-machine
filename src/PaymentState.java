import java.util.ArrayList;
import java.util.Scanner;

public class PaymentState extends State {
    
    private Machine machine;
	private Scanner scanner = new Scanner(System.in);
    private Money payment;

    public PaymentState(Machine machine) {
        this.machine = machine;
        this.payment = new Money();
        this.insertCoin();
    }

    private void proceed() {
        machine.setPaidMoney(payment);
        machine.setState(new OrderState(machine));
    }
    
    private void cancel() {
        this.dispenseMoney();
    }

    private void reset() {
        this.payment = new Money();
        this.insertCoin();
    }

    @Override
    public void insertCoin() {
		System.out.print(ConsoleColor.GREEN_BOLD);
		System.out.println("Payment");
		System.out.println("-----------------------");
		System.out.print(ConsoleColor.RESET);
		System.out.println();

        System.out.println("Number of Coins/Notes\n");
		String stringFormat = "%11s: ";
        ArrayList<String> moneyTypes = Money.getTypes();
        int numberOfCoinsOrNotes;
        for(String type : moneyTypes) {
            System.out.printf(stringFormat, type);
            numberOfCoinsOrNotes = scanner.nextInt();
            this.payment.add(type, numberOfCoinsOrNotes);
		}
		System.out.println();

        System.out.print("Paid: ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(this.payment.getAmount()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n");

        this.pressButton();
    }

    @Override
    public void pressButton() {
		String stringFormat = "%4s: %-10s";
        System.out.printf(stringFormat, "1", "Proceed");
        System.out.println();
        System.out.printf(stringFormat, "2", "Cancel");
		System.out.println("\n");
        System.out.print("> ");

        int choice = scanner.nextInt();
        
        if(choice == 1) {
            proceed();
        }
        else if(choice == 2) {
            cancel();
        }
    }

    @Override
    public void dispenseProduct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispenseMoney() {
        System.out.println();
        System.out.print("Dispensed: ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(this.payment.getAmount()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n");

		String stringFormat = "%10s: %d";
        ArrayList<String> moneyTypes = Money.getTypes();
        for(String type : moneyTypes) {
            System.out.printf(stringFormat, type, this.payment.getCount(type));
            System.out.println();
		}
		System.out.println();

        this.reset();
    }
}
