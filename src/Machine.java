public class Machine {
    private static Machine instance;

    private Machine() {}

    public static Machine getInstance() {
        if(instance==null) {
            instance = new Machine();
        }
        return instance;
    }

    
}
