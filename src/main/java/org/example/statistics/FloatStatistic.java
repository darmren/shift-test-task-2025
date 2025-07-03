package org.example.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс для сбора статистики по числам с плавающей точкой (float).
 * Вычисляет минимальное, максимальное значения, сумму и среднее.
 */
public class FloatStatistic extends Statistic {
    private BigDecimal minNum;
    private BigDecimal maxNum;
    private BigDecimal sum;
    private BigDecimal average;
    private BigDecimal quantity;

    /**
     * Создаёт экземпляр статистики для чисел float.
     * Инициализирует счётчики и сумму нулевыми значениями.
     */
    public FloatStatistic() {
        super("float");
        sum = BigDecimal.ZERO;
        average = BigDecimal.ZERO;
        quantity = BigDecimal.ZERO;
    }

    /**
     * Возвращает подробную статистику по числам с плавающей точкой.
     * Включает: минимум, максимум, сумму и среднее значение.
     *
     * @return строка с полной статистикой
     */
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

    /**
     * Обновляет статистику на основе очередной строки, представляющей число float.
     * Обновляет минимум, максимум, сумму, количество и количество символов.
     *
     * @param line строка, содержащая число в формате float
     */
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
