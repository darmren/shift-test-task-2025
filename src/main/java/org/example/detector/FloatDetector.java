package org.example.detector;

import org.example.Type;

import java.util.regex.Pattern;

/**
 * Детектор чисел с плавающей точкой.
 * <p>
 * Определяет, соответствует ли строка формату вещественного числа,
 * включая формат с экспонентой (например, 3.14, -2.0, 6.02E23).
 * </p>
 */
public class FloatDetector implements Detector<Type> {

    /**
     * Проверяет, является ли строка числом с плавающей точкой.
     *
     * @param line строка для анализа
     * @return {@link Type#FLOAT}, если строка соответствует числу с плавающей точкой, иначе {@code null}
     */
    @Override
    public Type detect(String line) {
        var stringRegex = "^-?\\d+\\.\\d+(E[-+]?\\d+)?$";
        if (Pattern.matches(stringRegex, line))
            return Type.FLOAT;
        return null;
    }
}

