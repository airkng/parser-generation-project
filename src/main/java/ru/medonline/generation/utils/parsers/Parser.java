package ru.medonline.generation.utils.parsers;

import ru.medonline.generation.utils.DataTypes;

import java.util.List;

public interface Parser {
    List<String> parse(String inputData);
}
