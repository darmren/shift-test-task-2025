package org.example.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntStatistic extends Statistic {
    private BigInteger minNum;
    private BigInteger maxNum;
    private BigInteger sum;
    private BigDecimal average;
    private BigInteger quantity;

    //    Pattern digitPattern = Pattern.compile( "(?<exp>-?\\d+\\.\\d+[eE][-+]?\\d+)|(?<float>-?\\d+\\.\\d+)|(?<int>-?\\d+)");
    public IntStatistic() {
        super("int");
        sum = BigInteger.valueOf(0);
        average = BigDecimal.ZERO;
        quantity = BigInteger.valueOf(0);
    }

    @Override
    public String showFull() {
        average = new BigDecimal(sum).divide(new BigDecimal(quantity), RoundingMode.HALF_UP);
        return String.format(
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
        var value = new BigInteger(line, 10);
        minNum = (minNum == null) ? value : minNum.min(value);
        maxNum = (maxNum == null) ? value : maxNum.max(value);
        charsNumber += line.length();
        sum = sum.add(value);
        quantity = quantity.add(BigInteger.ONE);
    }
}
