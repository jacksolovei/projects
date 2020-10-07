import accounts.BankAccount;
import accounts.CreditAccount;
import accounts.DepositAccount;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(); //BankAccount и его методы
        bankAccount.takeMoney(20.0);
        bankAccount.putMoney(60.0);
        bankAccount.getBalance();
        CreditAccount creditAccount = new CreditAccount(); //CreditAccount, снятие с процентами
        creditAccount.takeMoney(20.0);
        creditAccount.getBalance();
        DepositAccount depositAccount = new DepositAccount(); //DepositAccount, внесение средств меньше месяца назад
        depositAccount.putMoney(400.0);
        depositAccount.takeMoney(20.0);
        System.out.println(depositAccount.send(bankAccount, 50.0)); //false
        System.out.println(creditAccount.send(depositAccount, 20.0)); //true снятие денег с процентами
        creditAccount.getBalance();
    }
}
