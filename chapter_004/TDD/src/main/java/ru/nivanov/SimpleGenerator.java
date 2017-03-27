package ru.nivanov;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nikolay Ivanov on 20.03.2017.
 */
public class SimpleGenerator implements Template {
    private static final Pattern REGEX = Pattern.compile("\\$\\{(.+?)}");

    @Override
    public String generate(String template, Map<String, String> map) throws KeysException {
        Matcher mat = REGEX.matcher(template);
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keys = new HashSet<>();
        int keyCount = 0;
        while (mat.find()) {
            String key = mat.group(1);
            keys.add(key);
            if (map.containsKey(key)) {
                mat.appendReplacement(stringBuffer, map.get(key));
                keyCount++;
            }
        }
        mat.appendTail(stringBuffer);
        if (keyCount < keys.size()) {
            throw new KeysException("no some keys in map");
        } else if (map.size() > keys.size()) {
            throw new KeysException("unused keys in map found");
        }
        return stringBuffer.toString();
    }
}
