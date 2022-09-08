import java.util.ArrayList;
import java.util.Scanner;

public class OrderState extends State {
    
    private Machine machine;
	private Scanner scanner = new Scanner(System.in);
    
    public OrderState(Machine machine) {
        this.machine = machine;

        System.out.println();
		System.out.print(ConsoleColor.GREEN_BOLD);
		System.out.println("Order");
		System.out.println("-----------------------");
		System.out.print(ConsoleColor.RESET);
		System.out.println();

        // Debug
        // System.out.println(machine.getPaidMoney().getAmount());
        // System.out.println();
    
        this.pressButton();
    }
    
    @Override
    public void insertCoin() {
        this.machine.setState(new PaymentState(machine));
    }

    @Override
    public void pressButton() {

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
            this.placeOrder(productSelector-1);
        }
    }

    @Override
    public void dispenseProduct() {
        // TODO
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

    private void placeOrder(int productIndex) {
        Product product = this.machine.getInventory().get(productIndex);
        Money paidMoney = this.machine.getPaidMoney();

        System.out.println();
        System.out.print("Paid : ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(paidMoney.getAmount()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println();
        System.out.print("Price: ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(product.getPrice()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n");

        if(paidMoney.getAmount()<product.getPrice()) {
            this.denyOrder();
        }

        else {
            this.acceptOrder(product, paidMoney);
        }
    }

    private void denyOrder() {
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print("[Order Denied]");
        System.out.print(ConsoleColor.RESET);
        System.out.println("\n");

        System.out.println("You've got insufficient money. Please:\n");

        String stringFormat = "%4s: %-10s";
        System.out.printf(stringFormat, "1", "Select Another");
        System.out.println();
        System.out.printf(stringFormat, "2", "Insert More");
        System.out.println("\n");
        System.out.print("> ");

        int choice = scanner.nextInt();
        
        if(choice == 1) {
            this.pressButton();
        }
        else if(choice == 2) {
            this.insertCoin();
        }
    }

    private void acceptOrder(Product product, Money paidMoney) {
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print("[Order Accepted]");
        System.out.print(ConsoleColor.RESET);
        System.out.println("\n");

        MoneyReturn returnMoney = Money.calculateReturn(paidMoney, product.getPrice());
        System.out.println();
        System.out.println(returnMoney.getStatus());
        System.out.println(returnMoney.getMoney().getAmount());

    }
}
