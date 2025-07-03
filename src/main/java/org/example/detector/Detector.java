package org.example.detector;

/**
 * Функциональный интерфейс для определения типа данных по строке.
 *
 * @param <T> тип результата, который возвращает метод {@link #detect(String)}
 */
@FunctionalInterface
public interface Detector<T> {

    /**
     * Определяет тип переданной строки.
     *
     * @param line строка для анализа
     * @return определённый тип данных или {@code null}, если не удалось определить
     */
    T detect(String line);
}

