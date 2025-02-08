package org.example.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FloatStatistic extends Statistic {
    private BigDecimal minNum;
    private BigDecimal maxNum;
    private BigDecimal sum;
    private BigDecimal average;
    private BigDecimal quantity;

    public FloatStatistic() {
        super("float");
        sum = BigDecimal.ZERO;
        average = BigDecimal.ZERO;
        quantity = BigDecimal.ZERO;
    }

    @Override
    public String showFull() {
        average = sum.divide(quantity, RoundingMode.HALF_UP);
        return String.format(
                """
                        Тип данных %s
                        Число символов: %d
                        Наибольшее число: %s
                        Наименьшее число: %s
                        Сумма: %s
                        Среднее значение: %f
                        %n""",
                dataType, charsNumber, maxNum, minNum, sum, average
        );
    }

    @Override
    public void updateStatistic(String line) {
        var value = new BigDecimal(line);
        minNum = (minNum == null) ? value : minNum.min(value);
        maxNum = (maxNum == null) ? value : maxNum.max(value);
        charsNumber += line.length();
        sum = sum.add(value);
        quantity = quantity.add(BigDecimal.ONE);
    }
}
