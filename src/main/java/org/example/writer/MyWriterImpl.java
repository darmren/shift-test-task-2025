package org.example.writer;

import org.example.config.CliArgsConfig;
import org.example.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

/**
 * Реализация интерфейса {@link MyWriter}, записывающая строки в разные файлы
 * в зависимости от переданного типа ({@link Type}).
 */
public class MyWriterImpl implements MyWriter<Type> {
    private final HashMap<Type, Writer> writers;
    private final String outFilePath;
    private final CliArgsConfig config;

    /**
     * Конструктор, инициализирующий конфигурацию и выходной путь для файлов.
     *
     * @param config объект с аргументами командной строки
     */
    public MyWriterImpl(CliArgsConfig config) {
        this.config = config;
        outFilePath = setOutFilePath();
        writers = new HashMap<>();
    }

    /**
     * Закрывает все открытые выходные потоки.
     *
     * @throws IOException в случае ошибки при закрытии
     */
    @Override
    public void close() throws IOException {
        for (Writer writer : writers.values())
            writer.close();
    }

    /**
     * Записывает строку в соответствующий файл по её типу.
     * Если файл ещё не был создан, создаёт его.
     *
     * @param line строка данных
     * @param type тип данных для классификации
     * @throws IOException в случае ошибки записи
     */
    @Override
    public void write(String line, Type type) throws IOException {
        if (writers.get(type) == null) {
            writers.put(type, createWriter(type));
        }
        writers.get(type).write(line + '\n');
    }

    /**
     * Создаёт поток записи в файл, соответствующий переданному типу.
     *
     * @param type тип данных
     * @return объект {@link Writer}, привязанный к файлу
     * @throws IOException если файл не может быть создан
     */
    private Writer createWriter(Type type) throws IOException {
        File file = new File(outFilePath + type + ".txt");
        return new FileWriter(file, config.isAppending());
    }

    /**
     * Формирует путь к выходным файлам, используя директорию и префикс из конфигурации.
     *
     * @return базовый путь к выходным файлам
     */
    private String setOutFilePath() {
        var stringBuilder = new StringBuilder();
        if (config.getOutPath() != null) {
            stringBuilder.append(config.getOutPath());
            if (!config.getOutPath().endsWith("\\"))
                stringBuilder.append('\\');
        }
        if (config.getPrefix() != null)
            stringBuilder.append(config.getPrefix());
        return stringBuilder.toString();
    }
}
