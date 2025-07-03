package org.example.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.ToString;
import org.example.config.validation.FileNamesValidator;
import org.example.config.validation.OutPathValidation;
import org.example.config.validation.StatisticFlagsValidator;

import java.util.List;
/**
 * Класс конфигурации аргументов командной строки.
 * Используется для разбора входных параметров при запуске CLI-приложения.
 * <p>
 * Поддерживает валидацию через {@link StatisticFlagsValidator} и {@link FileNamesValidator}.
 * Используется библиотека JCommander.
 * </p>
 */
@ToString
@Getter
@Parameters(parametersValidators = StatisticFlagsValidator.class)
public class CliArgsConfig {

    /**
     * Список входных файлов.
     * Ожидаются валидные имена файлов. Валидация осуществляется через {@link FileNamesValidator}.
     */
    @Parameter(validateWith = FileNamesValidator.class)
    private List<String> files;

    /**
     * Директория, в которую будут сохраняться выходные файлы.
     * Задается с помощью ключа <code>-o</code>.
     * Валидируется через {@link OutPathValidation}.
     */
    @Parameter(names = "-o", description = "Директория для выходных файлов", validateWith = OutPathValidation.class)
    private String outPath;

    /**
     * Префикс, который будет добавлен к именам всех выходных файлов.
     * Указывается с помощью ключа <code>-p</code>.
     */
    @Parameter(names = "-p", description = "Префикс для выходных файлов")
    private String prefix;

    /**
     * Флаг, указывающий, нужно ли открывать выходные файлы на дозапись.
     * Устанавливается ключом <code>-a</code>.
     * По умолчанию — <code>false</code>.
     */
    @Parameter(names = "-a", description = "Открыть выходные файлы на дозапись")
    private boolean isAppending = false;

    /**
     * Флаг включения полной статистики.
     * Указывается с помощью ключа <code>-f</code>.
     */
    @Parameter(names = "-f", description = "Полная статистика")
    private boolean isFullStat = false;

    /**
     * Флаг включения краткой статистики.
     * Указывается с помощью ключа <code>-s</code>.
     */
    @Parameter(names = "-s", description = "Краткая статистика")
    private boolean isShortStat = false;
}

