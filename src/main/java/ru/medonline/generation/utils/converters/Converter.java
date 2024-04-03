package ru.medonline.generation.utils.converters;

public interface Converter<T> {
    T convert(String dirtyStr);
}
