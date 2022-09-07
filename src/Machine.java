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
    private Inventory inventory;
    private Product product;

    private Machine() {
        paymentState = new PaymentState(this);
		orderState = new OrderState(this);
		preparationState = new OrderState(this);
		dispenseState = new DispenseState(this);
		
    }

    public static Machine getInstance() {
        if(instance==null) {
            instance = new Machine();
        }
        return instance;
    }

	public void setState(State state) {
		this.state = state;
	}
}
