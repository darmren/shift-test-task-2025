package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));
        var config = CliArgsConfig.parseArgsToConfig(args);
        var filterUtil = new FilterUtil(config);
        filterUtil.filter();
    }
}