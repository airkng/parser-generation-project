package ru.medonline.generation.utils.writers;

import java.util.List;

public interface Writer<T> {
    boolean saveFile(T data, String path);

    boolean saveFile(List<T> data, String path);
}
