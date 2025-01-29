package org.example;

import java.io.IOException;

public class FilterUtil {
    private final CliArgsConfig config;

    public FilterUtil(CliArgsConfig config) {
        this.config = config;
    }

    public void filter() throws IOException {
        MyReader myReader = new MyReader(config.getFilesNames());
        MyWriter myWriter  = new MyWriter(config);
        while (myReader.ready()) {
            var line = myReader.readLine();
            myWriter.write(line);
        }
        myWriter.close();
    }


}
