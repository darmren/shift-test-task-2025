package org.example;

public class StringStatistic extends Statistic{
    private Integer linesNumber;
    private String maxLine;
    private String minLine;

    public StringStatistic(String dataType) {
        super(dataType);
        linesNumber = 0;
    }

    @Override
    public void showFull() {
        System.out.printf(
                """
                        Тип данных %s
                        Число символов %d
                        Число строк: %d
                        Самая длинная строка: %s
                        Самая короткая строка: %s
                        %n""",
                dataType,charsNumber, linesNumber, maxLine, minLine
        );
    }

    @Override
    public void updateStatistic(String line) {
        var lineLen = line.length();
        if (minLine == null || lineLen < minLine.length())
            minLine = line;
        if (maxLine == null || lineLen > maxLine.length())
            maxLine = line;
        charsNumber += lineLen;
        linesNumber += 1;
    }
}
