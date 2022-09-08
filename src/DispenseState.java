public class DispenseState extends State {

    private Machine machine;

    public DispenseState(Machine machine) {
        this.machine = machine;
        this.dispenseMoney();
    }
    
    @Override
    public void insertCoin() {
        /* Go to PaymentState */
        
    }

    @Override
    public void pressButton() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispenseProduct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispenseMoney() {
        System.out.println(this.machine.getReturnMoney().getStatus());
        System.out.println(this.machine.getReturnMoney().getMoney().getAmount());
    }
    
}
