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

    /* product variables */
	private String inventoryFilePath = Paths.get(Paths.get("").toAbsolutePath().toString(), "data", "inventory.txt").toString();
	private ArrayList<Product> inventory;
    private Product product;
	private int maxProductNameLength;

	/* others */
	private Scanner scanner = new Scanner(System.in);
	private File file;

	/* private constructor */

    private Machine() {

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
