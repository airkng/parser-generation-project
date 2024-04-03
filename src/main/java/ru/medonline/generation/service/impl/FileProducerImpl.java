package ru.medonline.generation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.medonline.generation.service.FileProducer;
import ru.medonline.generation.utils.parsers.Parser;
import ru.medonline.generation.utils.readers.Reader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileProducerImpl implements FileProducer {
    private final Parser parser;
    private final Reader reader;

    @Override
    public Map<Integer, List<String>> produce(String pathFrom) {
        String[] linesFromFile = reader.readLines(pathFrom);
        Map<Integer, List<String>> rowAndColumns = new LinkedHashMap<>();
        for (int i = 0; i < linesFromFile.length; i++) {
            List<String> parsedLine = parser.parse(linesFromFile[i]);
            rowAndColumns.put(i, parsedLine);
        }
        return rowAndColumns;
    }
}
