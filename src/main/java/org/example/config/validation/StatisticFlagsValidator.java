package org.example.config.validation;


import com.beust.jcommander.IParametersValidator;
import com.beust.jcommander.ParameterException;

import java.util.Map;

import static java.lang.Boolean.TRUE;

/**
 * Валидатор группы флагов, управляющих режимом вывода статистики.
 * <p>
 * Проверяет, что одновременно не указаны оба флага <code>-f</code> и <code>-s</code>,
 * и что хотя бы один из них задан.
 * </p>
 */
public class StatisticFlagsValidator implements IParametersValidator {

    /**
     * Валидирует совместимость флагов <code>-f</code> (полная статистика)
     * и <code>-s</code> (краткая статистика).
     *
     * @param parameters словарь всех переданных параметров и их значений
     * @throws ParameterException если оба флага заданы одновременно или не задан ни один
     */
    @Override
    public void validate(Map<String, Object> parameters) throws ParameterException {
        if (parameters.get("-f") == TRUE && parameters.get("-s") == TRUE)
            throw new ParameterException("Флаги выбора статистики -f, -s не могут быть применены вместе");
        else if (parameters.get("-f") == null && parameters.get("-s") == null)
            throw new ParameterException("Укажите флаг выбора статистики -f(полная) или -s(краткая)");
    }
}

