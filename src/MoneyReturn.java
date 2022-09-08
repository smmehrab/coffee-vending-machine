final class MoneyReturn {
    private final boolean status;
    private final Money money;

    public MoneyReturn(boolean status, Money money) {
        this.status = status;
        this.money = money;
    }

    public boolean getStatus() {
        return status;
    }

    public Money getMoney() {
        return money;
    }
}