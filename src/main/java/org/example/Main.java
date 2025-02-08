package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.example.detectors.*;
import org.example.statistics.FloatStatistic;
import org.example.statistics.IntStatistic;
import org.example.statistics.Statistic;
import org.example.statistics.StringStatistic;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var config = getConfig(args);
            var statistic = getStatistic(config);
            System.out.println(showStatistic(statistic, config));
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static HashMap<Type, Statistic> getStatistic(CliArgsConfig config) throws IOException {
        var filterUtil = new FilterUtil(config);
        var myReader = new MyReader(config.getFiles());
        var statistic = new HashMap<>(Map.of(Type.STRING, new StringStatistic(), Type.FLOAT, new FloatStatistic(), Type.INTEGER, new IntStatistic()));
        var detectors = new ArrayList<>(List.of(new StringDetector(), new IntDetector(), new FloatDetector()));
        filterUtil.filter(myReader, statistic, detectors);
        return statistic;
    }

    private static CliArgsConfig getConfig(String[] args) {
        var config = new CliArgsConfig();
        JCommander.newBuilder().addObject(config).build().parse(args);
        return config;
    }

    private static String showStatistic(HashMap<Type, Statistic> statistic, CliArgsConfig config) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statistic stat : statistic.values()) {
            if (config.isFullStat()) stringBuilder.append(stat.showFull());
            else if (config.isShortStat()) stringBuilder.append(stat.showShort());
        }
        return stringBuilder.toString();
    }
}