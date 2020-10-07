import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static final String REG_NAME = "[А-Я][а-я]+\\s+[А-Я][а-я]+\\s+[А-Я][а-я]+";
    public static final String REG_PHONE = "7[0-9]{10}";

    public static void main(String[] args) {

        TreeMap<String, String> nameMap = new TreeMap<>(); //phonebook with names and phone numbers
        nameMap.put("Иванов Иван Иванович", "79965911277");
        nameMap.put("Петров Петр Петрович", "79668244572");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите имя или номер телефона. Для просмотра списка введите команду LIST");
            String text = scanner.nextLine();
            if (text.equals("LIST")) {
                printMap(nameMap);
            } else if (text.matches(REG_NAME)) {
                if (!nameMap.containsKey(text)) {
                    System.out.println("Введите номер телефона для сохранения контакта");
                    String phoneNumber = scanner.nextLine();
                    checkPhone(nameMap, phoneNumber, text);
                } else {
                    System.out.println(text + " => " + nameMap.get(text) + "\n");
                }
            } else if (text.matches(REG_PHONE)) {
                if (!nameMap.containsValue(text)) {
                    System.out.println("Введите имя для сохранения контакта");
                    String name = scanner.nextLine();
                    checkName(nameMap, name, text);
                } else {
                    printValue(nameMap, text);
                }
            } else {
                System.out.println("Неверный ввод" + "\n");
            }
        }
    }

    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));
        }
    }

    private static void printValue(Map<String, String> map, String s) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(s)) {
                System.out.println(key + " => " + map.get(key) + "\n");
            }
        }
    }

    private static void checkPhone(Map<String, String> map, String phone, String nameInput) {
        if (phone.matches(REG_PHONE)) {
            map.put(nameInput, phone);
            System.out.println(nameInput + " => " + map.get(nameInput) + "\n");
        } else {
            System.out.println("Неверно введен номер телефона" + "\n");
        }
    }

    private static void checkName(Map<String, String> map, String nameInput, String phone) {
        if (nameInput.matches(REG_NAME)) {
            map.put(nameInput, phone);
            System.out.println(nameInput + " => " + phone + "\n");
        } else {
            System.out.println("Неверное введено имя" + "\n");
        }
    }
}
