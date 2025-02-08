package org.example.statistics;

public abstract class Statistic {
    Integer charsNumber;
    String dataType;

    public Statistic(String dataType){
        this.dataType = dataType;
        charsNumber = 0;
    }

    public String showShort(){
        return String.format("""
                        Тип данных %s
                        Число символов: %d
                        %n""",
                dataType, charsNumber);
    }

    public abstract String showFull();

    public abstract void updateStatistic(String line);
}
