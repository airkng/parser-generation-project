package ru.medonline.generation.service;

import java.util.List;
import java.util.Map;

public interface FileProducer {
    Map<Integer, List<String>> produce(String pathFrom);
}
