
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите Фамилию Имя Отчество дату_рождения номер_телефона пол (в формате: Фамилия Имя Отчество ДД.ММ.ГГГГ НомерТелефона Пол)");
            String input = scanner.nextLine();

            String[] parts = input.split(" ");
            if (parts.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите Фамилию Имя Отчество дату_рождения номер_телефона пол");

            }

            String lastName = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];

            if (isValidName(lastName) || isValidName(firstName) || isValidName(middleName)) {
                throw new IllegalArgumentException("Неверный формат Фамилии, Имени или Отчества. Допустимы только буквы.");
            }

            String birthDate = parts[3];
            if (!isValidDate(birthDate)) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат ДД.ММ.ГГГГ");
            }
            long phoneNumber = Long.parseLong(parts[4]);
            if (phoneNumber < 0) {
                throw new IllegalArgumentException("Номер телефона должен быть положительным числом.");
            }
            if (!parts[5].equals("m") && !parts[5].equals("f")) {
                throw new IllegalArgumentException("Пол должен быть 'm' или 'f'.");
            }

            String fileName = lastName + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write("<" + lastName + "> <" + firstName + "> <" + middleName + "> <" + birthDate + "> <" + phoneNumber + "> <" + parts[5] + ">\n");
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            System.err.println("Ошибка ввода номера телефона: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка во входных данных: " /*+ e.getMessage()*/);
            e.printStackTrace();
        }
    }
    private static boolean isValidDate(String date){
            return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }
    private static boolean isValidName(String name) {
        return !name.matches("[A-ZА-Я][a-zа-я]+");
    }
}
