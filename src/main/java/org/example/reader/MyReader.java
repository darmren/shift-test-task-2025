package org.example.reader;

import java.io.IOException;

/**
 * Интерфейс абстрактного последовательного чтения строк из источника данных.
 */
public interface MyReader {

    /**
     * Считывает одну строку из текущего источника.
     *
     * @return строка данных или {@code null}, если достигнут конец
     * @throws IOException в случае ошибок ввода-вывода
     */
    String readLine() throws IOException;

    /**
     * Проверяет, доступен ли источник для чтения.
     *
     * @return {@code true}, если можно читать; {@code false} — иначе
     * @throws IOException в случае ошибок ввода-вывода
     */
    boolean ready() throws IOException;

    /**
     * Закрывает все задействованные ресурсы.
     *
     * @throws IOException в случае ошибок закрытия
     */
    void close() throws IOException;
}

