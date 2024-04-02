package ru.medonline.generation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medonline.generation.service.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SimpleFileReader implements Reader {
    @Override
    public String[] readLines(String path) {
        List<String> linesList = new ArrayList<>(50);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            var line = reader.readLine();
            log.info("Начало полинейного считывания файла");
            while (line != null) {
                linesList.add(line);
                log.trace("Добавлена линия {}", line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Cannot read file: ", e);
        }
        return linesList.toArray(new String[]{});
    }

}
