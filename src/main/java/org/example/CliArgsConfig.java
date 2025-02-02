package org.example;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.validation.FileNamesValidator;
import org.example.validation.OutPathValidation;
import org.example.validation.StatisticFlagsValidator;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Parameters(parametersValidators = StatisticFlagsValidator.class)
public class CliArgsConfig {
    @Parameter(validateWith = FileNamesValidator.class)
    private List<String> files;

    @Parameter(names = "-o", description = "Директория для выходных файлов", validateWith = OutPathValidation.class)
    private String outPath;

    @Parameter(names = "-p", description = "Префикс для выходных файлов")
    private String prefix;

    @Parameter(names = "-a", description = "Открыть выходные файлы на дозапись")
    private boolean isAppending = false;

    @Parameter(names = "-f", description = "Полная статистика")
    private boolean isFullStat = false;

    @Parameter(names = "-s", description = "Краткая статистика")
    private boolean isShortStat = false;
}
