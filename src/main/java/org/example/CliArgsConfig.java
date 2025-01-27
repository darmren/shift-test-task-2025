package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class CliArgsConfig {
    private final List<String> filesNames;
    private final String outPath;
    private final String prefix;
    private final boolean isAppending;
    private final boolean isFullStat;

    public static CliArgsConfig parseArgsToConfig(String[] args) {
        var configBuilder = CliArgsConfig.builder();
        var files = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                    configBuilder.isFullStat(false);
                    break;
                case "-f":
                    configBuilder.isFullStat(true);
                    break;
                case "-a":
                    configBuilder.isAppending(true);
                    break;
                case "-o":
                    configBuilder.outPath(args[i + 1]);
                    break;
                case "-p":
                    configBuilder.prefix(args[i + 1]);
                    break;
                default:
                    if (args[i].contains(".txt"))
                        files.add(args[i]);
            }
        }
        configBuilder.filesNames(files);
        return configBuilder.build();
    }
}
