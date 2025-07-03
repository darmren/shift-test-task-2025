package org.example.config.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Валидатор параметра, указывающего выходную директорию.
 * <p>
 * Проверяет, существует ли указанная директория.
 * </p>
 */
public class OutPathValidation implements IParameterValidator {

    /**
     * Проверяет, что указанный путь существует и является директорией.
     *
     * @param name  имя параметра (например, <code>-o</code>)
     * @param value путь к директории
     * @throws ParameterException если директория не существует
     */
    @Override
    public void validate(String name, String value) throws ParameterException {
        var file = new File(value);
        if (!file.isDirectory())
            throw new ParameterException("Директории " + value + " не существует");
    }
}
