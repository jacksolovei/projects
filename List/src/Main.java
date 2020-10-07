import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду: " + "\nLIST - просмотр списка" + "\nADD + название дела - добавить новое дело" + "\nADD + номер дела + название дела - добавить дело на место другого" + "\nEDIT + номер дела + новое название дела - отредактировать название дела" + "\nDELETE + номер дела - удалить дело");
        String text = scanner.nextLine();
        String[] words = text.split("\\s+");

        ArrayList<String> toDoList = new ArrayList<>() {{
            add("Первое дело");
            add("Второе дело");
            add("Третье дело");
            add("Четвертое дело");
        }};

        switch (words[0]) {
            case "LIST":
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println((i + 1) + " " + toDoList.get(i));
                }
                break;
            case "ADD":
                if (words[1].matches("[0-9]+")) {
                    String replacedText = text.replaceAll("ADD\\s[0-9]+\\s", "");
                    toDoList.add(Integer.parseInt(words[1]) - 1, replacedText);
                    System.out.println("Добавлено новое дело: " + replacedText);
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.print("\n" + (i + 1) + " " + toDoList.get(i));
                    }
                } else {
                    String addedText = text.replaceAll("ADD\\s", "");
                    toDoList.add(addedText);
                    System.out.println("Добавлено новое дело: " + addedText);
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.print("\n" + (i + 1) + " " + toDoList.get(i));
                    }
                }
                break;
            case "EDIT":
                String editedText = text.replaceAll("EDIT\\s[0-9]+\\s", "");
                toDoList.remove(Integer.parseInt(words[1]) - 1);
                toDoList.add(Integer.parseInt(words[1]) - 1, editedText);
                System.out.println("Дело номер " + words[1] + " изменено на " + editedText);
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.print("\n" + (i + 1) + " " + toDoList.get(i));
                }
                break;
            case "DELETE":
                toDoList.remove(Integer.parseInt(words[1]) - 1);
                System.out.println("Дело номер " + words[1] + " удалено");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.print("\n" + (i + 1) + " " + toDoList.get(i));
                }
                break;
            default:
                System.out.println("Команда введена неверно");
                break;
        }
    }
}
