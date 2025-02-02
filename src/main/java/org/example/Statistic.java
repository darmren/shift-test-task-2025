package org.example;

abstract class Statistic {
    Integer charsNumber;
    String dataType;

    public Statistic(String dataType){
        this.dataType = dataType;
        charsNumber = 0;
    }

    public void showShort(){
        System.out.printf("""
                        Тип данных %s
                        Число символов: %d
                        %n""",
                dataType, charsNumber);
    }

    public abstract void showFull();

    public abstract void updateStatistic(String line);
}
