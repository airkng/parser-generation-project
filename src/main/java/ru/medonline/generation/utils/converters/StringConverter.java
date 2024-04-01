package ru.medonline.generation.utils.converters;

public interface StringConverter<T> {
    T convert(String dirtyStr);
}
