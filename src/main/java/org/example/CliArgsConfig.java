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
        int i = 0;
        while (i < args.length) {
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
                    i++;
                    break;
                case "-p":
                    configBuilder.prefix(args[i + 1]);
                    i++;
                    break;
                default:
                    if (args[i].contains(".txt"))
                        files.add(args[i]);
                    else {
                        System.out.println("Неверный формат ввода. Повторите попытку\n");
                        return null;
                    }
            }
            i++;
        }
        configBuilder.filesNames(files);
        return configBuilder.build();
    }
}
