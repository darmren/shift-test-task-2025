package org.example.reader;

import com.beust.jcommander.ParameterException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link MyReader}, позволяющая последовательно читать строки
 * из нескольких текстовых файлов поочередно (циклический переход между файлами).
 */
public class MyReaderImpl implements MyReader {
    private final List<BufferedReader> readers;
    private int currentReader;

    /**
     * Конструктор принимает список имён файлов и инициализирует соответствующие {@link BufferedReader}.
     *
     * @param fileNames список имён входных файлов
     * @throws FileNotFoundException если хотя бы один файл не найден
     * @throws ParameterException если список файлов равен {@code null}
     */
    public MyReaderImpl(List<String> fileNames) throws FileNotFoundException {
        if (fileNames == null)
            throw new ParameterException("Список входных файлов не может быть пустым");
        currentReader = 0;
        readers = new ArrayList<>();
        for (String fileName : fileNames) {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            readers.add(bufferedReader);
        }
    }

    /**
     * Проверяет, доступен ли текущий или следующий файл для чтения.
     *
     * @return {@code true}, если есть доступные строки
     * @throws IOException если произошла ошибка чтения
     */
    @Override
    public boolean ready() throws IOException {
        if (readers.isEmpty())
            return false;
        var res = readers.get(currentReader).ready();
        if (!res) {
            readers.get(currentReader).close();
            readers.remove(readers.get(currentReader));
            currentReader -= 1;
            nextReader();
        }
        return res || ready();
    }

    /**
     * Закрывает все открытые ридеры.
     *
     * @throws IOException если возникает ошибка при закрытии
     */
    @Override
    public void close() throws IOException {
        for (Reader reader : readers) {
            reader.close();
        }
    }

    /**
     * Переключается на следующий доступный ридер в списке.
     */
    public void nextReader() {
        currentReader = (currentReader == readers.size() - 1) ? 0 : currentReader + 1;
    }

    /**
     * Считывает строку из текущего ридера и переходит к следующему.
     *
     * @return строка из файла или {@code null}, если достигнут конец
     * @throws IOException если возникает ошибка чтения
     */
    @Override
    public String readLine() throws IOException {
        var res = readers.get(currentReader).readLine();
        nextReader();
        return res;
    }
}
