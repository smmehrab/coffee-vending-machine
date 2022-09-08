import java.util.ArrayList;
import java.util.Scanner;

public class OrderState extends State {
    
    private Machine machine;
	private Scanner scanner = new Scanner(System.in);
    
    public OrderState(Machine machine) {
        this.machine = machine;
        this.pressButton();
    }
    
    @Override
    public void insertCoin() {
        // TODO Auto-generated method stub
    }

    @Override
    public void pressButton() {
        System.out.println();
		System.out.print(ConsoleColor.GREEN_BOLD);
		System.out.println("Order");
		System.out.println("-----------------------");
		System.out.print(ConsoleColor.RESET);
		System.out.println();

        // Debug
        // System.out.println(machine.getPaidMoney().getAmount());
        // System.out.println();

        System.out.println("Choose Your Coffee:\n");

        int numberOfProducts = this.machine.getNumberOfProducts();
		String stringFormat = "  %";
        stringFormat += Integer.toString(this.machine.getNumberOfProducts());
        stringFormat += "s: %-";
		stringFormat += Integer.toString(this.machine.getMaxProductNameLength());
		stringFormat += "s%n";
        int productSelector = 1;
		for(Product product : this.machine.getInventory()) {
			System.out.printf(stringFormat, productSelector++, product.getName());
		}
        System.out.printf(stringFormat, 0, "Cancel Order");
		System.out.println();

        System.out.print("> ");
        productSelector = scanner.nextInt();

        if(productSelector == 0) {
            this.cancelOrder();
        }
        else if(productSelector>=1 && productSelector<=numberOfProducts) {

        }
        else {

        }
    }

    @Override
    public void dispenseProduct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispenseMoney() {
        Money paidMoney = this.machine.getPaidMoney();

        System.out.println();
        System.out.print("Dispensed: ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(paidMoney.getAmount()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n");

		String stringFormat = "%10s: %d";
        ArrayList<String> moneyTypes = Money.getTypes();
        for(String type : moneyTypes) {
            System.out.printf(stringFormat, type, paidMoney.getCount(type));
            System.out.println();
		}
		System.out.println();
        
    }
    
    private void cancelOrder() {
        dispenseMoney();

        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print("[Order Cancelled]");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n\n");

        this.machine.setState(new PaymentState(machine));
    }
}
