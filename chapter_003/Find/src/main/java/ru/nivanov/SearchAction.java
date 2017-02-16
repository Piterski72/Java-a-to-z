package ru.nivanov;

import java.io.IOException;
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
     * chat bot acton.
     * @return string result..
     * @throws IOException ..
     */
    @SuppressWarnings("JavaDoc")
    ArrayList<String> execute(String name, Path path);
}