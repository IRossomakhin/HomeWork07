import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScanner {

    static public ArrayList<String> readFile(String filepath) {
        ArrayList<String> superpositionOfWords = new ArrayList<>();
        System.out.println("Введите порядковый номер слова");
        Scanner sc = new Scanner(System.in);
        int wordNumber = sc.nextInt();
        try {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {// использована конструкция try-with-resource

                while (true) {
                    String lineFromFile = reader.readLine();
                    if (lineFromFile == null) {
                        break;
                    }
                    String[] lineElement = lineFromFile.split(" ");
                    try {
                        superpositionOfWords.add(lineElement[wordNumber - 1]);
                    } catch (Exception e) {
                        System.err.println("В этой строке нет " + wordNumber + " слова.\nСообщение об ошибке: " + e.getMessage());//вывод ошибки
                        return superpositionOfWords; //вывод сообщения об ошибке + код ошибки
                    }
                }
                System.out.println(superpositionOfWords);
                return superpositionOfWords;

            } catch (IOException e) {
                throw new RuntimeException("Что-то пошло не так");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();//Это не  rethrow - переделать
            throw e ;
        }
        //return superpositionOfWords;

    }

    public static void main(String[] args) {
        System.out.println("Находим каждое N слово в строке из файла Text1");
        readFile("Text1.txt");
        System.out.println("_______________________________\nНаходим каждое N слово в строке из файла Text2");
        readFile("Text2.txt");
    }
}
