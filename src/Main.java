import java.io.File;
import java.util.Scanner;

public class Main {
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
        }
    }
}