package ru.medonline.generation.utils.parsers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medonline.generation.utils.parsers.Parser;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CsvParser implements Parser {
    //todo: понять как работает
    @Override
    public List<String> parse(String line) {
        String[] splitedText = line.split(",");
        ArrayList<String> columnList = new ArrayList<String>();
        for (int i = 0; i < splitedText.length; i++) {
            //Если колонка начинается на кавычки или заканчиваеться на кавычки
            if (IsColumnPart(splitedText[i])) {
                String lastText = columnList.get(columnList.size() - 1);
                columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
            } else {
                columnList.add(splitedText[i]);
            }
        }
        return columnList;
    }

    private boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
