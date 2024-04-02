package ru.medonline.generation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medonline.generation.model.ParsedData;
import ru.medonline.generation.service.Writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
@Slf4j
public class SimpleFileWriter implements Writer<String> {
    @Override
    public boolean saveFile(String request, String pathTo) {
        try {
            Path file = Paths.get(pathTo);
            Files.write(file, List.of(request), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            log.error("Ошибка во время записи в файл {}", pathTo, e);
            return false;
        }
    }

    @Override
    public boolean saveFile(List<String> data, String pathTo) {
        try {
            Path file = Paths.get(pathTo);
            Files.write(file, data, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            log.error("Ошибка во время записи в файл {}", pathTo, e);
            return false;
        }
    }
}
