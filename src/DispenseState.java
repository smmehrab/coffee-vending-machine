import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogManager;

public class DispenseState extends State {

    private Machine machine;

    public DispenseState(Machine machine) {
        this.machine = machine;

        System.out.println();
		System.out.print(ConsoleColor.GREEN_BOLD);
		System.out.println("Dispense");
		System.out.println("-----------------------");
		System.out.print(ConsoleColor.RESET);
		System.out.println();
        
        // Debug
        // System.out.println(this.machine.getReturnMoney().getStatus());
        // System.out.println(this.machine.getReturnMoney().getMoney().getAmount());

        this.dispenseMoney();
        this.prepareCoffee();
        this.dispenseProduct();
    }
    
    @Override
    public void insertMoney() {
        /* Go to PaymentState */
        this.machine.clearPaidMoney();
        this.machine.setState(new PaymentState(machine));
    }

    @Override
    public void interactWithMenu() {
        /* Invalid for DispenseState */
    }

    @Override
    public void dispenseProduct() {
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.println("[" + this.machine.getChosenProduct().getName() + " Ready]");
        System.out.print(ConsoleColor.RESET);
        System.out.println();

        System.out.print("Press ENTER to take what's yours...");
        char[] temp = System.console().readPassword();

        System.out.println("");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.println("[Have A Nice Day]");
        System.out.print(ConsoleColor.RESET);
        System.out.println("\n");

        this.insertMoney();
    }

    @Override
    public void dispenseMoney() {
        Money returnMoney = this.machine.getReturnMoney().getMoney();

        System.out.print("Dispensed: ");
        System.out.print(ConsoleColor.GREEN_BRIGHT);
        System.out.print(Integer.toString(returnMoney.getAmount()) + " Cents");
        System.out.print(ConsoleColor.RESET);
		System.out.println("\n");

		String stringFormat = "%10s: %d";
        ArrayList<String> moneyTypes = Money.getTypes();
        for(String type : moneyTypes) {
            System.out.printf(stringFormat, type, returnMoney.getCount(type));
            System.out.println();
		}
		System.out.println();
        
        this.machine.updateMachineMoney("return");
        this.machine.clearReturnMoney();
    }

    private void prepareCoffee() {
        int preparationTime = this.machine.getChosenProduct().getPreparationTime();

        System.out.println("Preparing Your " + this.machine.getChosenProduct().getName() +" ...");
        System.out.println("[" + preparationTime + " seconds]");
        System.out.println();

        System.out.print(ConsoleColor.GREEN_BRIGHT);

        for (int i = 0; i <= preparationTime; i += 1) {
            progressPercentage(i, preparationTime);
            try {
                Thread.sleep(1000);
            } 
            catch (Exception e) {
                
            }
        }
        
        System.out.print(ConsoleColor.RESET);
        System.out.println();
    }
    
    private void progressPercentage(int done, int total) {
        int size = 21;
        String iconLeftBoundary = "[";
        String iconDone = "=";
        String iconRemain = ".";
        String iconRightBoundary = "]";

        if (done > total) {
            throw new IllegalArgumentException();
        }
        int donePercents = (100 * done) / total;
        int doneLength = size * donePercents / 100;

        StringBuilder bar = new StringBuilder(iconLeftBoundary);
        for (int i = 0; i < size; i++) {
            if (i < doneLength) {
                bar.append(iconDone);
            } else {
                bar.append(iconRemain);
            }
        }
        bar.append(iconRightBoundary);

        System.out.print("\r" + bar + " " + donePercents + "%");

        if (done == total) {
            System.out.print("\n");
        }
    }
}
