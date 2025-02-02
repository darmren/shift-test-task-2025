package org.example;

public class FloatStatistic extends Statistic {
    private Double minNum;
    private Double maxNum;
    private Double sum;
    private Double average;
    private Double quantity;

    public FloatStatistic(String dataType) {
        super(dataType);
        sum = 0.0;
        average = 0.0;
        quantity = 0.0;
    }

    @Override
    public void showFull() {
        average = sum / quantity;
        System.out.printf(
                """
                        Тип данных %s
                        Число символов: %d
                        Наибольшее число: %f
                        Наименьшее число: %f
                        Сумма: %f
                        Среднее значение: %f
                        %n""",
                dataType, charsNumber, maxNum, minNum, sum, average
        );
    }

    @Override
    public void updateStatistic(String line) {
        try {var value = Double.parseDouble(line);
            if (minNum == null || value < minNum)
                minNum = value;
            if (maxNum == null || value > maxNum) {
                maxNum = value;
            }
            charsNumber += line.length();
            sum += value;
            quantity += 1;}
        catch (NumberFormatException e){
            System.out.printf("%s невозможно представить в виде вещественного числа%n", line);
        }
    }
}
