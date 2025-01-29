package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MyReader extends Reader {
    private final List<BufferedReader> readers;
    private int currentReader;

    public MyReader(List<String> fileNames) throws FileNotFoundException {
        currentReader = 0;
        readers = new ArrayList<>();
        for (String fileName : fileNames) {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            readers.add(bufferedReader);
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        var res = readers.get(currentReader).read(cbuf, off, len);
        nextReader();
        return res;
    }

    @Override
    public boolean ready() throws IOException {
        if (readers.isEmpty())
            return false;
        var res = readers.get(currentReader).ready();
        if (!res) {
            readers.get(currentReader).close();
            readers.remove(readers.get(currentReader));
            currentReader -= 1;
            nextReader();
        }
        return res || ready();
    }

    @Override
    public void close() throws IOException {
        for (Reader reader : readers) {
            reader.close();
        }
    }

    public void nextReader() {
        currentReader = (currentReader == readers.size() - 1) ? 0 : currentReader + 1;
    }

    public String readLine() throws IOException {
        var res = readers.get(currentReader).readLine();
        nextReader();
        return res;
    }
}
