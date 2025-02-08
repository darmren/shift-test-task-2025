package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class MyWriter extends Writer {
    private final HashMap<Type, Writer> writers;
    private final String outFilePath;
    private final CliArgsConfig config;

    public MyWriter(CliArgsConfig config) {
        this.config = config;
        outFilePath = setOutFilePath();
        writers = new HashMap<>();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (Writer writer : writers.values())
            writer.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        for (Writer writer : writers.values())
            writer.flush();
    }

    @Override
    public void close() throws IOException {
        for (Writer writer : writers.values())
            writer.close();
    }

    public void write(String line, Type type) throws IOException {
        if (writers.get(type) == null) {
            writers.put(type, createWriter(type));
        }
        writers.get(type).write(line + '\n');
    }

    private Writer createWriter(Type type) throws IOException {
        File file = new File(outFilePath + type + ".txt");
        if (config.isAppending())
            return new FileWriter(file, true);
        return new FileWriter(file);
    }

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
