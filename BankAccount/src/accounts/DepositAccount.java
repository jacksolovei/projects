package accounts;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate date;

    public DepositAccount() {
        date = LocalDate.of(2018, 7, 26);
    }

    //метод для проверки наличия средств с датой
    protected boolean checkSum(double money) {
        LocalDate dateTakeMoney = LocalDate.now();
        if (balance >= money) {
            if (date.plusMonths(1).isBefore(dateTakeMoney)) {
                return true;
            } else {
                System.out.println("В этом месяце уже нельзя снять деньги со счета");
                return false;
            }
        } else {
            System.out.println("Недостаточно средств на счете");
            return false;
        }
    }

    //метод для внесения средств с датой
    public void putMoney(double moneyPut) {
        date = LocalDate.now();
        balance = balance + moneyPut;
    }
}