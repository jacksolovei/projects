package accounts;

public class BankAccount {
    protected double balance;

    public BankAccount() {
        balance = 100.0;
    }

    //метод для проверки наличия средств
    protected boolean checkSum(double money) {
        if (balance >= money) {
            return true;
        } else {
            System.out.println("Недостаточно средств на счете");
            return false;
        }
    }

    //метод для снятия средств
    public void takeMoney(double moneyTaken) {
        if (checkSum(moneyTaken)) {
            balance = balance - moneyTaken;
        }
    }

    //метод для внесения средств
    public void putMoney(double moneyPut) { //метод для внесения средств
        balance = balance + moneyPut;
    }

    //метод для проверки баланса
    public Double getBalance() {
        System.out.println("Баланс счета = " + balance);
        return balance;
    }

    //метод для отправки средств на счет
    public boolean send(BankAccount receiver, double amount) {
        if (checkSum(amount)) {
            takeMoney(amount);
            receiver.putMoney(amount);
            return true;
        } else {
            return false;
        }
    }
}
