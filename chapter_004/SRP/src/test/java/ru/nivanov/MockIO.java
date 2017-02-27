package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 27.02.2017.
 */
public class MockIO implements IO {
    private final StringBuilder out = new StringBuilder();

    /**
     * Read method.
     * @return ..
     */
    @Override
    public String read() {
        return this.out.toString();
    }

    /**
     * Print method.
     * @param value ..
     */
    @Override
    public void println(Object value) {
        this.out.append(value);
        this.out.append(System.getProperty("line.separator"));
    }

}
