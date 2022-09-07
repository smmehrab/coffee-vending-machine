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

        System.out.println(machine.getPaidMoney().getAmount());
        System.out.println();
    }

    @Override
    public void dispenseProduct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispenseMoney() {
        // TODO Auto-generated method stub
        
    }
    
}
