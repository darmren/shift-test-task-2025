package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FilterUtil {
    private final CliArgsConfig config;
    private final String stringRegex;
    private final String integerRegex;
    private final String floatRegex;
    private final HashMap<String, Statistic> statistics;
    public FilterUtil(CliArgsConfig config) {
        this.config = config;
        stringRegex = "^[a-zA-Zа-яА-Я ]+$";
        integerRegex = "^-?\\d+$";
        floatRegex = "^-?\\d+\\.\\d+(E[-+]?\\d+)?$";
        statistics = new HashMap<>(Map.of("intStatistic", new IntStatistic("integer"),
                "floatStatistic", new FloatStatistic("float"),
                "stringStatistic", new StringStatistic("string")));
    }

    public void filter() throws IOException {
        MyReader myReader = new MyReader(config.getFilesNames());
        MyWriter myWriter  = new MyWriter(config);
        while (myReader.ready()) {
            var line = myReader.readLine();
            if (Pattern.matches(integerRegex, line)){
                myWriter.write(line, "integerWriter");
                statistics.get("intStatistic").updateStatistic(line);
            }
            else if (Pattern.matches(floatRegex, line)) {
                myWriter.write(line, "floatWriter");
                statistics.get("floatStatistic").updateStatistic(line);
            }
            else if (Pattern.matches(stringRegex, line)){
                myWriter.write(line, "stringWriter");
                statistics.get("stringStatistic").updateStatistic(line);
            }
        }
        myWriter.close();
    }

    public void showStatistic(){
        for (Statistic stat : statistics.values()) {
            if (config.isFullStat())
                stat.showFull();
            else
                stat.showShort();
        }
    }
}
