import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Machine {

	/* private instance variable for Singleton Class */
    private static Machine instance;

    /* current state */
    private State state;

    /* possible states */
    private State paymentState;
    private State orderState;
    private State preparationState;
    private State dispenseState;

    /* money variables */
    private Money machineMoney;
    private Money insertedMoney;

    /* product variables */
    private ArrayList<Product> inventory;
    private Product product;
	private String inventoryFilePath = Paths.get(Paths.get("").toAbsolutePath().toString(), "data", "inventory.txt").toString();

	/* others */
	private Scanner scanner;
	private File file;

	/* private constructor */

    private Machine() {
        this.paymentState = new PaymentState(instance);
		this.orderState = new OrderState(instance);
		this.preparationState = new OrderState(instance);
		this.dispenseState = new DispenseState(instance);
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
}
