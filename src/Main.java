import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static class LongLineException extends RuntimeException {
        public LongLineException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fileCount = 0;

        while (true) {
            System.out.println("Введите путь и нажмите <Enter>: ");
            String path = scanner.nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExist) {
                System.out.println("Путь не существует. Попробуйте снова.");
                continue;
            }

            if (isDirectory) {
                System.out.println("Это директория, а не файл. Попробуйте снова.");
                continue;
            }

            fileCount++;
            System.out.println("Путь указан верно. Это файл номер " + fileCount);

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                int totalLines = 0;
                int maxLength = 0;
                int minLength = Integer.MAX_VALUE;

                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();

                    if (length > 1024) {
                        throw new LongLineException("Строка длиннее 1024 символов: " + length + " символов");
                    }

                    totalLines++;
                    if (length > maxLength) maxLength = length;
                    if (length < minLength) minLength = length;
                }

                System.out.println("Общее количество строк: " + totalLines);
                System.out.println("Максимальная длина строки: " + maxLength);
                System.out.println("Минимальная длина строки: " + minLength);

            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            } catch (LongLineException e) {
                System.out.println("Критическая ошибка: " + e.getMessage());
                System.exit(1);
            } finally {
                try {
                    reader.close();
                } catch (IOException | NullPointerException e) {
                    System.out.println("Ошибка при закрытии файла: " + e.getMessage());
                }
            }
        }
    }
}