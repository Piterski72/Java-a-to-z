package ru.nivanov.netFileManager;

import java.io.IOException;

/**
 * BaseAction interface.
 * @author nivanov.
 */
interface BaseAction {
    /**
     * Getting key ident for event.
     * @return desc..
     */
    String key();

    /**
     * Menu acton.
     * @throws IOException ..
     */
    void execute() throws IOException;
}