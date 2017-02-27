package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 22.02.2017.
 */
public interface CalcActions {

    /**
     * execute.
     * @param validator ..
     * @param reuse ..
     */
    void execute(Validator validator, boolean reuse);

    /**
     * info.
     * @return ..
     */
    String info();

    /**
     * key.
     * @return ..
     */
    int key();
}