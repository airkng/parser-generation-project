package ru.medonline.generation.utils.converters;

import ru.medonline.generation.utils.converters.Converter;

import java.time.LocalDate;

public interface DateConverter extends Converter<LocalDate> {
     void addNewFormat(String format);
}
