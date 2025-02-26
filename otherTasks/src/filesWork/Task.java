package filesWork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Task {
//1.	Написать метод, который читает текстовый файл и возвращает его в виде списка строк.
public static List<String> readFileToList(String filePath) throws IOException {
    return Files.readAllLines(Paths.get(filePath));
    }

//2.	Написать метод, который записывает в файл строку, переданную параметром.
    public static void writeStringToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE);
    }
//3.	Используя решение 1 и 2, напишите метод, который склеивает два текстовый файла один.
    public static void mergeFiles(String filePath1, String filePath2, String outputFilePath) throws IOException {
        // Чтение содержимого файлов
        List<String> lines1 = readFileToList(filePath1);
        List<String> lines2 = readFileToList(filePath2);
        // Склеивание содержимого
        StringBuilder mergedContent = new StringBuilder();
        for (String line : lines1) {
            mergedContent.append(line).append(System.lineSeparator());
        }
        for (String line : lines2) {
            mergedContent.append(line).append(System.lineSeparator());
        }
        writeStringToFile(outputFilePath, mergedContent.toString());
    }
//4.	Написать метод который заменяет в файле все кроме букв и цифр на знак ‘$’
    public static void replaceNonAlphanumeric(String filePath) throws IOException {
        List<String> lines = readFileToList(filePath);
        StringBuilder modifiedContent = new StringBuilder();
        for (String line : lines) {
            String modifiedLine = line.replaceAll("[^a-zA-Z0-9]", "$");
            modifiedContent.append(modifiedLine).append(System.lineSeparator());
        }
        writeStringToFile(filePath, modifiedContent.toString());
    }
}
