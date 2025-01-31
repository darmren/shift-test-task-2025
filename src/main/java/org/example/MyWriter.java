package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyWriter extends Writer {
    private final HashMap<String, Writer> writers;
    private final Pattern stringPattern;
    private final Pattern digitPattern;
    private final String outFilePath;
    private final CliArgsConfig config;
    public StringStatistic stringStatistic;

    public MyWriter(CliArgsConfig config){
        this.config = config;
        var stringRegex = "\\D[a-zA-Zа-яА-Я]\\D+";
        var digitRegex = "(?<float>-?(\\d+\\.\\d+)([eE][-+]?\\d+)?)|(?<int>-?\\d+)";
        stringPattern = Pattern.compile(stringRegex);
        digitPattern = Pattern.compile(digitRegex);
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
        stringStatistic.showFull();
        for (Writer writer : writers.values())
            writer.close();
    }

    public void write(String line) throws IOException {
        Matcher stringMatcher = stringPattern.matcher(line);
        Matcher digitMatcher = digitPattern.matcher(line);

        while (digitMatcher.find()) {
            var floatGroup = digitMatcher.group("float");
            var intGroup = digitMatcher.group("int");
            if (floatGroup != null)
                useNecessaryWriter(floatGroup, "floatWriter");
            if (intGroup != null)
                useNecessaryWriter(intGroup, "integerWriter");
        }
        while (stringMatcher.find()) {
            var strGroup = stringMatcher.group();
            useNecessaryWriter(strGroup, "stringWriter");
            updateStringStatistic(strGroup);
        }
    }

    private void useNecessaryWriter(String line, String writerType) throws IOException {
        if (writers.get(writerType) == null){
            var dataType = writerType.replace("Writer", "");
            writers.put(writerType, createWriter(dataType));}
        writers.get(writerType).write(line + '\n');
    }

    private void updateStringStatistic(String line){
        if (stringStatistic == null)
            stringStatistic = new StringStatistic("String");
        stringStatistic.updateStatistic(line);
    }

    private Writer createWriter(String dataType) throws IOException {
        File file = new File(outFilePath + dataType + ".txt");
        if (config.isAppending())
            return new FileWriter(file, true);
        return new FileWriter(file);
    }

    private String setOutFilePath() {
        var stringBuilder = new StringBuilder();
        if (config.getOutPath() != null)
            stringBuilder.append(config.getOutPath());
        if (config.getPrefix() != null)
            stringBuilder.append(config.getPrefix());
        return stringBuilder.toString();
    }
}
