package org.example;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterUtil {
    private final CliArgsConfig config;
    private final String outFilePath;

    private final Pattern stringPattern;
    private final Pattern digitPattern;

    private Writer integerWriter;
    private Writer floatWriter;
    private Writer stringWriter;

    public FilterUtil(CliArgsConfig config) {
        this.config = config;
        var stringRegex = "\\D[a-zA-Zа-яА-Я]\\D+";
        var digitRegex = "(?<float>-?(\\d+\\.\\d+)([eE][-+]?\\d+)?)|(?<int>-?\\d+)";
        stringPattern = Pattern.compile(stringRegex);
        digitPattern = Pattern.compile(digitRegex);
        outFilePath = setOutFilePath();
    }

    public void filter() throws IOException {
        MyReader myReader = new MyReader(config.getFilesNames());
        while (myReader.ready()) {
            var line = myReader.readLine();
            writeToNecessaryFile(line);
        }
        closeWriters();
    }

    private void writeToNecessaryFile(String line) throws IOException {
        Matcher stringMatcher = stringPattern.matcher(line);
        Matcher digitMatcher = digitPattern.matcher(line);

        while (digitMatcher.find()){
            var floatGroup = digitMatcher.group("float");
            var intGroup = digitMatcher.group("int");
            if (floatGroup != null)
                writeToFloatFile(floatGroup);
            if (intGroup != null)
                writeToIntegerFile(intGroup);
        }
        while (stringMatcher.find()) {
            writeToStringFile(stringMatcher.group());
        }
    }

    private void writeToIntegerFile(String line) throws IOException {
        if (integerWriter == null)
            integerWriter = createWriter("integer");
        integerWriter.write(line + '\n');
    }

    private void writeToFloatFile(String line) throws IOException {
        if (floatWriter == null)
            floatWriter = createWriter("float");
        floatWriter.write(line + '\n');
    }

    private void writeToStringFile(String line) throws IOException {
        if (stringWriter == null)
            stringWriter = createWriter("string");
        stringWriter.write(line + '\n');
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

    private void closeWriters() throws IOException {
        if (integerWriter != null)
            integerWriter.close();
        if (floatWriter != null)
            floatWriter.close();
        if (stringWriter != null)
            stringWriter.close();
    }

}
