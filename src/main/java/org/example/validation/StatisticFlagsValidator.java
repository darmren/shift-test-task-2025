package org.example.validation;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IParametersValidator;
import com.beust.jcommander.ParameterException;

import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class StatisticFlagsValidator implements IParametersValidator {

    @Override
    public void validate(Map<String, Object> parameters) throws ParameterException {
        if (parameters.get("-f") == TRUE && parameters.get("-s") == TRUE)
            throw new ParameterException("Флаги выбора статистики -f, -s не могут быть применены вместе");
        else if (parameters.get("-f") == null && parameters.get("-s") == null)
            throw new ParameterException("Укажите флаг выбора статистики -f(полная) или -s(краткая)");
    }
}
