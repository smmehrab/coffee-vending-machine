public class App {
    public static void main(String[] args) throws Exception {
        Machine machine = Machine.getInstance();
        machine.loadInventory();
        machine.start();
    }
}
