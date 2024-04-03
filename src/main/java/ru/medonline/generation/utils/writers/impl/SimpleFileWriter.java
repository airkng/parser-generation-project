package ru.medonline.generation.utils.writers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medonline.generation.utils.writers.Writer;

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
        return save(List.of(request), pathTo);
    }

    private boolean save(List<String> request, String pathTo) {
        try {
            Path file = Paths.get(pathTo);
            if (Files.exists(file)) {
                Files.write(file, request, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } else {
                //todo: дописать чтобы создался такой файл
            }
            return true;
        } catch (IOException e) {
            log.error("Ошибка во время записи в файл {}", pathTo, e);
            return false;
        }
    }

    @Override
    public boolean saveFile(List<String> data, String pathTo) {
        return save(data, pathTo);
    }
}
