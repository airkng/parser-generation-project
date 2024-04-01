package ru.medonline.generation.utils.converters.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.medonline.generation.utils.converters.StringConverter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DateTimeConverter implements StringConverter<LocalDate> {
    private final DateTimeFormatter df0 = DateTimeFormatter.ofPattern("dd MM yyyy");
    private final DateTimeFormatter df8 = DateTimeFormatter.ofPattern("d MM yyyy");
    private final DateTimeFormatter df1 = DateTimeFormatter.ofPattern("d MM yy");
    private final DateTimeFormatter df2 = DateTimeFormatter.ofPattern("d M yy");
    private final DateTimeFormatter df3 = DateTimeFormatter.ofPattern("d M yyyy");
    private final DateTimeFormatter df4 = DateTimeFormatter.ofPattern("dd M yyyy");
    private final DateTimeFormatter df5 = DateTimeFormatter.ofPattern("dd M yy");
    private final DateTimeFormatter df6 = DateTimeFormatter.ofPattern("dd MM yy");

    private final DateTimeFormatter df15 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DateTimeFormatter df7 = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private final DateTimeFormatter df9 = DateTimeFormatter.ofPattern("d.MM.yy");
    private final DateTimeFormatter df10 = DateTimeFormatter.ofPattern("d.M.yy");
    private final DateTimeFormatter df11 = DateTimeFormatter.ofPattern("d.M.yyyy");
    private final DateTimeFormatter df12 = DateTimeFormatter.ofPattern("dd.M.yyyy");
    private final DateTimeFormatter df13 = DateTimeFormatter.ofPattern("dd.M.yy");
    private final DateTimeFormatter df14 = DateTimeFormatter.ofPattern("dd.MM.yy");

    private final DateTimeFormatter df16 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final DateTimeFormatter df17 = DateTimeFormatter.ofPattern("M/dd/yyyy");
    private final DateTimeFormatter df18 = DateTimeFormatter.ofPattern("M/dd/yy");
    private final DateTimeFormatter df19 = DateTimeFormatter.ofPattern("M/d/yy");
    private final DateTimeFormatter df20 = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final DateTimeFormatter df21 = DateTimeFormatter.ofPattern("MM/d/yyyy");
    private final DateTimeFormatter df22 = DateTimeFormatter.ofPattern("MM/d/yy");
    private final DateTimeFormatter df23 = DateTimeFormatter.ofPattern("MM/dd/yy");
    private List<DateTimeFormatter> formatterList = new ArrayList<>(25);

    {
        formatterList.add(df0);
        formatterList.add(df1);
        formatterList.add(df2);
        formatterList.add(df3);
        formatterList.add(df4);
        formatterList.add(df5);
        formatterList.add(df6);
        formatterList.add(df7);
        formatterList.add(df8);
        formatterList.add(df9);
        formatterList.add(df10);
        formatterList.add(df11);
        formatterList.add(df12);
        formatterList.add(df13);
        formatterList.add(df14);
        formatterList.add(df15);
        formatterList.add(df16);
        formatterList.add(df17);
        formatterList.add(df18);
        formatterList.add(df19);
        formatterList.add(df20);
        formatterList.add(df21);
        formatterList.add(df22);
        formatterList.add(df23);
    }

    @Override
    public LocalDate convert(String dirtyStr) {
        return convertToLocalDate(dirtyStr);
    }

    private LocalDate convertToLocalDate(String date) {
        log.trace("Начало конвертации: {}", date);
        String cleared = clearSymbols(date);
        LocalDate localDate = null;
        Exception e = null;
        for (DateTimeFormatter formatter : formatterList) {
            try {
                return LocalDate.parse(cleared, formatter);
            } catch (DateTimeParseException ex) {
                 e = ex;
                log.info("Конвертация с {} прошла неуспешно", formatter);
                continue;
            }
        }
        log.error("", e);
        throw new DateTimeException("Парсинг прошел неуспешно");
    }

    public void addNewFormat(String format) {
        formatterList.add(DateTimeFormatter.ofPattern(format));
    }

    private String clearSymbols(String date) {
        return date.trim()
                .replaceAll("[a-zA-ZА-Яа-я]", "")
                .trim();
    }
}
