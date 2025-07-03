package org.example.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Класс для сбора статистики по целочисленным значениям.
 * Подсчитывает минимальное, максимальное значения, сумму, среднее и общее количество символов.
 */
public class IntStatistic extends Statistic {
    private BigInteger minNum;
    private BigInteger maxNum;
    private BigInteger sum;
    private BigDecimal average;
    private BigInteger quantity;

    /**
     * Создаёт экземпляр статистики для целых чисел.
     * Инициализирует счётчики нулями.
     */
    public IntStatistic() {
        super("int");
        sum = BigInteger.valueOf(0);
        average = BigDecimal.ZERO;
        quantity = BigInteger.valueOf(0);
    }

    /**
     * Возвращает полную статистику:
     * минимальное и максимальное значения, сумму, среднее значение и общее количество символов.
     *
     * @return строка с полной статистикой
     */
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

    /**
     * Обновляет статистику на основе новой строки, содержащей целое число.
     *
     * @param line строка с целочисленным значением
     */
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
