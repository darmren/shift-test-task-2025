package org.example.detector;

import org.example.Type;

import java.util.regex.Pattern;

/**
 * Детектор строк (текста).
 * <p>
 * Определяет, содержит ли строка только буквы (латиница или кириллица) и пробелы.
 * </p>
 */
public class StringDetector implements Detector<Type> {

    /**
     * Проверяет, является ли строка текстовой (без цифр и спецсимволов).
     *
     * @param line строка для анализа
     * @return {@link Type#STRING}, если строка состоит только из букв и пробелов, иначе {@code null}
     */
    @Override
    public Type detect(String line) {
        var stringRegex = "^[a-zA-Zа-яА-Я ]+$";
        if (Pattern.matches(stringRegex, line))
            return Type.STRING;
        return null;
    }
}

