public class Transaction {
    private String name;
    private Double expense;
    private Integer income;

    public Transaction(String name, Double expense, Integer income) {
        this.name = name;
        this.expense = expense;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public Double getExpense() {
        return expense;
    }

    public Integer getIncome() {
        return income;
    }
}
