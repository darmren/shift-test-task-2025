package org.example.detectors;

import org.example.Type;

import java.util.regex.Pattern;

public class FloatDetector implements Detector<Type> {
    @Override
    public Type detect(String line) {
        var stringRegex = "^-?\\d+\\.\\d+(E[-+]?\\d+)?$";
        if (Pattern.matches(stringRegex, line))
            return Type.FLOAT;
        return null;
    }
}
