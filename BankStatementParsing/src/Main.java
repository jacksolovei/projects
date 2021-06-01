import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class Main {

    public static final String FILE_PATH = "data/movementList.csv";

    public static void main(String[] args) {
        int sumIncome = 0;
        double sumExpenses = 0.0;
        ArrayList<Transaction> transactions = loadFromFile();
        for (Transaction transaction : transactions) {
            sumIncome += transaction.getIncome();
            sumExpenses += transaction.getExpense();
        }
        System.out.println("Сумма доходов: " + sumIncome + " рублей" + "\nСумма расходов: " + sumExpenses + " рублей");

        Map<String, Double> expenses = transactions.stream()
                .collect(groupingBy(Transaction::getName, summingDouble(Transaction::getExpense)));

        System.out.println("\nСуммы расходов по организациям:");
        for (String key : expenses.keySet()) {
            System.out.println(key + " - " + expenses.get(key) + " рублей");
        }
    }

    private static ArrayList<Transaction> loadFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] fragments = line.split(",", 8);
                if (fragments[6].matches("[0-9]+") && fragments[7].matches("\"?[0-9]*(,[0-9]*\")?")) {
                    String replacedLine = fragments[7].replace("\"", "").replace(",", ".");
                    double expenses = Double.parseDouble(replacedLine);
                    int income = Integer.parseInt(fragments[6]);
                    String temp = fragments[5].replace("\\", "/");
                    String payment = temp.substring(temp.indexOf("/"), temp.indexOf("       ")).replace("/", " ").trim();
                    transactions.add(new Transaction(payment, expenses, income));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
