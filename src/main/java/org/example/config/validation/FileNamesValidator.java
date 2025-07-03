package org.example.config.validation;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Валидатор параметров командной строки, проверяющий имена входных файлов.
 * <p>
 * Допускаются только существующие файлы с расширением <code>.txt</code>.
 * </p>
 */
public class FileNamesValidator implements IParameterValidator {

    /**
     * Проверяет, что указанный параметр является существующим файлом с расширением .txt.
     *
     * @param name  имя параметра (например, <code>--file</code>)
     * @param value значение параметра (имя файла)
     * @throws ParameterException если файл не существует или не имеет расширения .txt
     */
    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!value.contains(".txt"))
            throw new ParameterException("Файлы должны быть с расширением .txt (найдено: " + value + ")");
        var file = new File(value);
        if (!file.exists())
            throw new ParameterException("Файл " + value + " не существует");
    }
}

