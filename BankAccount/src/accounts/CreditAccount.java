package accounts;

public class CreditAccount extends BankAccount {
    public void takeMoney(double moneyTaken) {
        //метод для снятия денег с процентом
        if (checkSum(moneyTaken + moneyTaken / 100)) {
            balance = balance - moneyTaken - moneyTaken / 100;
        }
    }
}
