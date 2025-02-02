package org.example.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class FileNamesValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.contains(".txt"))
            throw new ParameterException("Файлы должны быть с расширением .txt (найдено: " + value +")");
        var file = new File(value);
        if (!file.exists())
            throw new ParameterException("Файл " + value + " не существует");
    }
}
