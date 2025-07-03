package org.example.statistics;

/**
 * Класс для сбора статистики по строковым данным.
 * Подсчитывает количество строк, символов, а также находит самую короткую и длинную строку.
 */
public class StringStatistic extends Statistic {
    private Integer linesNumber;
    private String maxLine;
    private String minLine;

    /**
     * Создаёт экземпляр статистики для строк.
     * Инициализирует счётчик строк нулём.
     */
    public StringStatistic() {
        super("string");
        linesNumber = 0;
    }

    /**
     * Возвращает полную статистику по строкам:
     * общее число строк, количество символов, самую длинную и короткую строку.
     *
     * @return строка с полной статистикой
     */
    @Override
    public String showFull() {
        return String.format("""
                Тип данных %s
                Число символов %d
                Число строк: %d
                Самая длинная строка: %s
                Самая короткая строка: %s
                %n""", dataType, charsNumber, linesNumber, maxLine, minLine);
    }

    /**
     * Обновляет статистику на основе новой строки:
     * увеличивает счётчики, а также обновляет информацию о минимальной и максимальной строке.
     *
     * @param line строка, которую нужно учесть в статистике
     */
    @Override
    public void updateStatistic(String line) {
        var lineLen = line.length();
        if (minLine == null || lineLen < minLine.length()) minLine = line;
        if (maxLine == null || lineLen > maxLine.length()) maxLine = line;
        charsNumber += lineLen;
        linesNumber += 1;
    }
}
