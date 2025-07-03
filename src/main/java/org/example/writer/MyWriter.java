package org.example.writer;

import org.example.Type;

import java.io.IOException;

/**
 * Интерфейс абстракции для записи строк, классифицированных по типу.
 *
 * @param <T> тип классификации (например, {@link Type})
 */
public interface MyWriter<T> {

    /**
     * Записывает строку, отнесённую к определённому типу.
     *
     * @param line строка для записи
     * @param type тип данных, определяющий выходной файл
     * @throws IOException в случае ошибки записи
     */
    void write(String line, T type) throws IOException;

    /**
     * Закрывает все открытые ресурсы.
     *
     * @throws IOException в случае ошибки закрытия
     */
    void close() throws IOException;
}

