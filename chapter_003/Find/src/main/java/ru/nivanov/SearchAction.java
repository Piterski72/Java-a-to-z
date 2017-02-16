package ru.nivanov;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * BotAction interface.
 * @author nivanov.
 */
interface SearchAction {
    /**
     * Getting string ident for event.
     * @return id..
     */
    String getId();

    /**
     * @param name ..
     * @param path ..
     * @return arraylist
     */
    @SuppressWarnings("JavaDoc")
    ArrayList<String> execute(String name, Path path);
}