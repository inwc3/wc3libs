package net.moonlightflower.wc3libs.misc.exeversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

class CallableInputStreamProcessor implements Callable<Integer> {
    private final InputStream inputStream;
    private final Consumer<String> lineConsumer;

    protected CallableInputStreamProcessor(InputStream inputStream, Consumer<String> lineConsumer) {
        this.inputStream = inputStream;
        this.lineConsumer = lineConsumer;
    }

    @Override
    public Integer call() {
        String line;
        Integer linesRead = 0;
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));

        while (true) {
            try {
                line = bufReader.readLine();
                if (line == null) {
                    break;
                } else {
                    linesRead++;
                    lineConsumer.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return linesRead;
    }
}
