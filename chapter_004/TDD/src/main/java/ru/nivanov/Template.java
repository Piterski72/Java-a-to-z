package ru.nivanov;

import java.util.Map;

/**
 * Created by Nikolay Ivanov on 20.03.2017.
 */
interface Template {
    /**
     * Generator for inserting keys values in template.
     * @param template ..
     * @param map ..
     * @return ..
     */
    String generate(String template, Map<String, String> map);
}
