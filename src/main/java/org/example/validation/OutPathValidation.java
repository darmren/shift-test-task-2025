package org.example.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class OutPathValidation implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        var file = new File(value);
        if (!file.isDirectory())
            throw new ParameterException("Директории " + value + " не существует");
    }
}
