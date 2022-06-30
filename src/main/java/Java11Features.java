package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Java11Features {
    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Predicate<Integer> checkForZero = integer -> integer == 0;
    List<Integer> integerList = new ArrayList<>();

    private void readFile(BufferedReader read) {
        try {
            String line;
            while ((line = read.readLine()) != null)
                integerList.add(Integer.parseInt(line.trim()));
        } catch (Exception e) {
            log.info("Error" + e.getMessage());
        }
    }

    private void writeFile(){
        try{
            //create the temporary file
            Path path = Files.createTempFile(Path.of("src/main/resources"), "Output", ".txt");
            integerList.stream()
                    .filter(Predicate.not(checkForZero))
                    .forEach(i -> {
                        try {
                            i=i+5;
                            Files.write(path, String.valueOf(i).getBytes(), StandardOpenOption.APPEND);
                            Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            log.info("Exception" + e.getMessage());
                        }
                    });
        }catch (Exception e){
            log.info("Exception" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Java11Features java11Features = new Java11Features();
            String file = "src/main/resources/Input.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            java11Features.readFile(bufferedReader);
            java11Features.writeFile();
        } catch (Exception e) {
            log.info("Error" + e.getMessage());
        }
    }
}
