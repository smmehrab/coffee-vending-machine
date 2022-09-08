import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Machine {

	/* private instance variable for Singleton Class */
    private static Machine instance;

    /* machine state */
    private State state;

    /* money variables */
    private Money machineMoney;
    private Money paidMoney;
	private MoneyReturn returnMoney;

    /* product variables */
	private ArrayList<Product> inventory;
    private Product product;
	private int maxProductNameLength;

	/* file & i/o */
	private String inventoryFilePath = Paths.get(Paths.get("").toAbsolutePath().toString(), "data", "inventory.txt").toString();
	private String moneyFilePath = Paths.get(Paths.get("").toAbsolutePath().toString(), "data", "money.txt").toString();

	private Scanner scanner = new Scanner(System.in);
	private File file;

	/* private constructor */

    private Machine() {
		this.paidMoney = new Money();
		this.returnMoney = new MoneyReturn(false, new Money());
    }

	/* public getInstance() */

    public static Machine getInstance() {
        if(instance==null) {
            instance = new Machine();
        }
        return instance;
    }

	public void setState(State state) {
		this.state = state;
	}

	public void loadInventory() {
		file = new File(inventoryFilePath);
        try {
            scanner = new Scanner(file);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		inventory = new ArrayList<>();

		while(scanner.hasNextLine()) {
			String[] productDetails = scanner.nextLine().split(",");
			String name = productDetails[0];

			ProductType type = ProductType.Coffee;
			switch(name) {
				case "Cappuccino":
					type = ProductType.Cappuccino;
					break;
				default:
					break;
			}

			int price = Integer.parseInt(productDetails[1]);
			int preparationTime = Integer.parseInt(productDetails[2]);
			int amountLeft = Integer.parseInt(productDetails[3]);

			inventory.add(new Product(name, type, price, preparationTime, amountLeft));
		}

		// Debug
		// for(Product product : inventory) {
		// 	System.out.println(product.getName());
		// 	System.out.println(product.getType().toString());
		// 	System.out.println(product.getPrice());
		// 	System.out.println(product.getPreparationTime());
		// 	System.out.println(product.getAmountLeft());	
		// }
	}

	public void loadMoney() {
		file = new File(moneyFilePath);
        try {
            scanner = new Scanner(file);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		machineMoney = new Money();
		while(scanner.hasNextLine()) {
			String[] coinOrNoteDetails = scanner.nextLine().split(",");
			String coinOrNoteType = coinOrNoteDetails[0] + " Cents";
			int coinOrNoteCount = Integer.parseInt(coinOrNoteDetails[1]);
			machineMoney.add(coinOrNoteType, coinOrNoteCount);
		}

		// Debug
		// System.out.println("Machine Money: " + this.machineMoney.getAmount());
	}

	public void start() {

		System.out.println();
		System.out.print(ConsoleColor.GREEN_BOLD);
		System.out.println("Coffee Vending Machine");
		System.out.println("-----------------------");
		System.out.print(ConsoleColor.RESET);
		System.out.println();

		maxProductNameLength = -1;
		for(Product product : inventory) {
			maxProductNameLength = Math.max(maxProductNameLength, product.getName().length());
		}

		String stringFormat = "%-";
		stringFormat += Integer.toString(maxProductNameLength);
		stringFormat += "s %5d Cents%n";
		for(Product product : inventory) {
			System.out.printf(stringFormat, product.getName(), product.getPrice());
		}
		System.out.println();

		this.setState(new PaymentState(this));
	}

	public void setPaidMoney(Money money) {
		this.paidMoney = money;
	}

	public Money getPaidMoney() {
		return this.paidMoney;
	}

	public void clearPaidMoney() {
		this.paidMoney.reset();
	}

	public void setReturnMoney(MoneyReturn moneyReturn) {
		this.returnMoney = moneyReturn;
	}

	public MoneyReturn getReturnMoney() {
		return this.returnMoney;
	}

	public void clearReturnMoney() {
		this.returnMoney = new MoneyReturn(false, new Money());
	}

	public int getMaxProductNameLength() {
		return maxProductNameLength;
	}

	public ArrayList<Product> getInventory() {
		return inventory;
	}

	public int getNumberOfProducts() {
		return inventory.size();
	}
	
}
