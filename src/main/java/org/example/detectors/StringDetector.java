package org.example.detectors;

import org.example.Type;

import java.util.regex.Pattern;

public class StringDetector implements Detector<Type> {
    @Override
    public Type detect(String line) {
        var stringRegex = "^[a-zA-Zа-яА-Я ]+$";
        if (Pattern.matches(stringRegex, line))
            return Type.STRING;
        return null;
    }
}
