import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес сайта");
        String input = scanner.nextLine();

        if (!input.matches("(http://)?(www\\.)?[a-z]+\\.[a-z]{2,3}")) {
            System.out.println("Введен неверный адрес");
        } else {
            String url = "https://" + input.replaceAll("(http://)?(www\\.)?", "") + "/";
            UrlNode urlNode = new UrlNode(url);
            TreeSet<String> sortedUrls = new ForkJoinPool().invoke(new WebProcessor(urlNode));

            try {
                Files.write(Paths.get("data/list.txt"), sortedUrls);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
