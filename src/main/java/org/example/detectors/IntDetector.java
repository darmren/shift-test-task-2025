package org.example.detectors;

import org.example.Type;

import java.util.regex.Pattern;

public class IntDetector implements Detector<Type> {
    @Override
    public Type detect(String line) {
        var stringRegex = "^-?\\d+$";
        if (Pattern.matches(stringRegex, line))
            return Type.INTEGER;
        return null;
    }
}
