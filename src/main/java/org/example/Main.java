package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));
        //var config = CliArgsConfig.parseArgsToConfig(args);
        var config = new CliArgsConfig();
        try{
            JCommander.newBuilder().addObject(config).build().parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        var filterUtil = new FilterUtil(config);
        filterUtil.filter();
        filterUtil.showStatistic();
    }
}