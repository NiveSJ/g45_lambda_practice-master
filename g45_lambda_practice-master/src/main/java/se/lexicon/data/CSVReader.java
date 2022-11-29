package se.lexicon.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {

    private final static CSVReader INSTANCE;

    static {
        INSTANCE = new CSVReader();
    }

    private CSVReader() {
    }

    public static CSVReader getInstance() {
        return INSTANCE;
    }

    public List<String> getMaleFirstNames() {
        String fileName = "C:\\Users\\Administratör.000\\IdeaProjects\\g45_lambda_practice-master" +
                "\\g45_lambda_practice-master" +
                "\\src\\main\\java\\se\\lexicon\\data\\firstname_males.txt";
        BufferedReader reader = null;
        List<String> names = null;
        try {
            Path filePath = Paths.get(fileName);
            System.out.println(filePath.toAbsolutePath());
            reader = Files.newBufferedReader(filePath);
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return names;
    }


    public List<String> getFemaleFirstNames() {
        List<String> names = null;
        String fileName = "C:\\Users\\Administratör.000\\IdeaProjects" +
                "\\g45_lambda_practice-master\\g45_lambda_practice-master" +
                "\\src\\main\\java\\se\\lexicon\\data\\firstname_female.txt";
        Path filePath = Paths.get(fileName);
        System.out.println(filePath.toAbsolutePath());

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {


            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }


    public List<String> getLastNames() {

        List<String> names = null;
        BufferedReader reader = null;
        String fileName = "C:\\Users\\Administratör.000\\IdeaProjects\\g45_lambda_practice-master" +
                "\\g45_lambda_practice-master\\src\\main\\java\\se\\lexicon" +
                "\\data\\lastnames.txt";

        try {
            Path filePath = Paths.get(fileName);
            reader = Files.newBufferedReader(filePath);

            System.out.println(filePath.toAbsolutePath());

            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return names;
    }


}
