package org.example.util;

import org.example.detector.Detector;
import org.example.reader.MyReader;
import org.example.statistics.Statistic;
import org.example.writer.MyWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Утилитный класс для фильтрации строк по их типу и обработки соответствующей статистики.
 * <p>
 * Использует список детекторов для определения типа каждой строки,
 * обновляет соответствующую статистику и записывает данные через {@link MyWriter}.
 *
 * @param <T> Тип, определяемый детектором (например, {@code Type})
 */
public class FilterUtil<T> {

    /**
     * Читает строки из {@link MyReader}, определяет их тип с помощью {@link Detector},
     * обновляет соответствующую {@link Statistic} и записывает результат через {@link MyWriter}.
     *
     * @param statistics отображение типа данных в объект статистики
     * @param detectors  список детекторов, определяющих тип строки
     * @param myReader   источник строк
     * @param myWriter   приёмник отфильтрованных строк
     * @throws IOException в случае ошибки чтения или записи
     */
    public void filter(HashMap<T, Statistic> statistics,
                       List<Detector<T>> detectors,
                       MyReader myReader,
                       MyWriter<T> myWriter) throws IOException {
        while (myReader.ready()) {
            var line = myReader.readLine();
            for (Detector<T> detector : detectors) {
                var type = detector.detect(line);
                if (type != null) {
                    statistics.get(type).updateStatistic(line);
                    myWriter.write(line, type);
                }
            }
        }
        myReader.close();
        myWriter.close();
    }
}
