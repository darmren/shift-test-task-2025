package org.example;

import org.example.detectors.Detector;
import org.example.statistics.Statistic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class FilterUtil {
    private final CliArgsConfig config;

    public FilterUtil(CliArgsConfig config) {
        this.config = config;
    }

    public void filter(MyReader myReader, HashMap<Type, Statistic> statistics, List<Detector<Type>> detectors) throws IOException {
        MyWriter myWriter = new MyWriter(config);
        while (myReader.ready()) {
            var line = myReader.readLine();
            for (Detector<Type> detector : detectors) {
                var type = detector.detect(line);
                if (type != null) {
                    statistics.get(type).updateStatistic(line);
                    myWriter.write(line, type);
                }
            }
        }
        myWriter.close();
    }
}
