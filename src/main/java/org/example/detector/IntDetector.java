package org.example.detector;

import org.example.Type;

import java.util.regex.Pattern;

/**
 * Детектор целых чисел.
 * <p>
 * Определяет, соответствует ли строка формату целого числа (например, 10, -42).
 * </p>
 */
public class IntDetector implements Detector<Type> {

    /**
     * Проверяет, является ли строка целым числом.
     *
     * @param line строка для анализа
     * @return {@link Type#INTEGER}, если строка является целым числом, иначе {@code null}
     */
    @Override
    public Type detect(String line) {
        var stringRegex = "^-?\\d+$";
        if (Pattern.matches(stringRegex, line))
            return Type.INTEGER;
        return null;
    }
}

