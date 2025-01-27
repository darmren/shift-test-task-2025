package org.example;

import java.io.*;
import java.util.regex.Pattern;

public class FilterUtil {
    private final CliArgsConfig config;
    //    private final Pattern stringPattern = Pattern.compile("^[a-zA-Zа-яА-Я ]+$");
    private final String stringRegex;
    private final String integerRegex;
    private final String floatRegex;
    private final String outFilePath;
    private Writer integerWriter;
    private Writer floatWriter;
    private Writer stringWriter;

    public FilterUtil(CliArgsConfig config) {
        this.config = config;
        stringRegex = "^[a-zA-Zа-яА-Я ]+$";
        integerRegex = "^-?\\d+$";
        floatRegex = "^-?\\d+\\.\\d+$";
        outFilePath = setOutFilePath();
    }

    public void filter() throws IOException {
        for (String fileName : config.getFilesNames()) {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                var line = bufferedReader.readLine();
                writeToNecessaryFile(line);
            }
        }
        closeWrites();
    }

    private void writeToNecessaryFile(String line) throws IOException {
        if (Pattern.matches(integerRegex, line))
            writeToIntegerFile(line);
        else if (Pattern.matches(floatRegex, line))
            writeToFloatFile(line);
        else if (Pattern.matches(stringRegex, line))
            writeToStringFile(line);
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

    private String setOutFilePath(){
        var stringBuilder = new StringBuilder();
        if (config.getOutPath() != null)
            stringBuilder.append(config.getOutPath());
        if (config.getPrefix() != null)
            stringBuilder.append(config.getPrefix());
        return stringBuilder.toString();
    }

    private void closeWrites() throws IOException {
        if (integerWriter != null)
            integerWriter.close();
        if (floatWriter != null)
            floatWriter.close();
        if (stringWriter != null)
            stringWriter.close();
    }

}
