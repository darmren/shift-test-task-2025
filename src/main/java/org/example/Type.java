package org.example;

/**
 * Перечисление возможных типов данных, определяемых детекторами.
 * <ul>
 *     <li>{@link #STRING} — строка (текстовые данные)</li>
 *     <li>{@link #FLOAT} — число с плавающей точкой</li>
 *     <li>{@link #INTEGER} — целое число</li>
 * </ul>
 */
public enum Type {
    /**
     * Строковый тип — только буквы и пробелы.
     */
    STRING,

    /**
     * Число с плавающей точкой (например, 3.14, -2.0, 6.02E23).
     */
    FLOAT,

    /**
     * Целое число (например, 42, -100).
     */
    INTEGER;

    /**
     * Возвращает строковое представление типа в нижнем регистре.
     *
     * @return имя типа, приведённое к нижнему регистру (например, {@code "float"})
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
