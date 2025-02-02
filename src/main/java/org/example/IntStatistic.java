package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntStatistic extends Statistic {
    private Integer minNum;
    private Integer maxNum;
    private Integer sum;
    private Float average;
    private Integer quantity;

    //    Pattern digitPattern = Pattern.compile( "(?<exp>-?\\d+\\.\\d+[eE][-+]?\\d+)|(?<float>-?\\d+\\.\\d+)|(?<int>-?\\d+)");
    public IntStatistic(String dataType) {
        super(dataType);
        sum = 0;
        average = 0.0F;
        quantity = 0;
    }

    @Override
    public void showFull() {
        average = sum.floatValue() / quantity;
        System.out.printf(
                """
                        Тип данных %s
                        Число символов %d
                        Наибольшее число: %d
                        Наименьшее число: %d
                        Сумма: %d
                        Среднее значение: %f
                        %n""",
                dataType, charsNumber, maxNum, minNum, sum, average
        );

    }

    @Override
    public void updateStatistic(String line) {
        try {var value = Integer.parseInt(line);
            if (minNum == null || value < minNum)
                minNum = value;
            if (maxNum == null || value > maxNum) {
                maxNum = value;
            }
            charsNumber += line.length();
            sum += value;
            quantity += 1;}
        catch (NumberFormatException e){
            System.out.printf("%s невозможно представить в виде Integer%n", line);
        }
    }
}
