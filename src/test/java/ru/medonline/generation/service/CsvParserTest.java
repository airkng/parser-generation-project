package ru.medonline.generation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.medonline.generation.model.RowData;
import ru.medonline.generation.utils.parsers.Parser;


@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CsvParserTest {
    //private final Parser<RowData> parser;

}
