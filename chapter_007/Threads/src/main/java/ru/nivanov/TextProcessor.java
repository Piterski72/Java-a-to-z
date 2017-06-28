package ru.nivanov;

import java.util.StringTokenizer;

/**
 * Created by Nikolay Ivanov on 26.06.2017.
 */
class TextProcessor {
    private final String line;

    /**
     * Constructor.
     * @param line ..
     */
    TextProcessor(String line) {
        this.line = line;
    }

    /**
     * Counts words in text line.
     * @return ..
     */
    int wordsCount() {
        StringTokenizer stringTokenizer = new StringTokenizer(this.line, " ");
        return stringTokenizer.countTokens();
    }

    /**
     * Counts spaces in text line.
     * @return ..
     */
    int spacesCount() {
        int spaces = 0;
        char[] temp = this.line.toCharArray();
        for (char var : temp) {
            if (var == ' ') {
                spaces++;
            }
        }
        return spaces;

    }
}
