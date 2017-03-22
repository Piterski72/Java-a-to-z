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
    @Override
    public String generate(String template, Map<String, String> map) {
        Pattern pat = Pattern.compile("\\$\\{(.+?)}");
        Matcher mat = pat.matcher(template);
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keys = new HashSet<>();
        while (mat.find()) {
            String key = mat.group(1);
            keys.add(key);
            if (map.containsKey(key)) {
                mat.appendReplacement(stringBuffer, map.get(key));
            }
        }
        mat.appendTail(stringBuffer);
        System.out.println(verifyKeysInMap(keys, map));
        return stringBuffer.toString();
    }

    /**
     * Method for check if less or more keys in map than needed.
     * @param input ..
     * @param map ..
     * @return ..
     */
    private String verifyKeysInMap(Set<String> input, Map<String, String> map) {
        String result = "";
        int count = 0;
        String[] matches = new String[input.size()];
        String[] mapKeys = new String[map.size()];
        matches = input.toArray(matches);
        mapKeys = map.keySet().toArray(mapKeys);
        if (mapKeys.length < matches.length) {
            result = "no some keys in map";
        } else {
            for (String match : matches) {
                for (String mapKey : mapKeys) {
                    if (match.equals(mapKey)) {
                        count++;
                    }
                }
            }
            if (count < mapKeys.length) {
                result = "unused keys in map found";
            }
        }
        return result;
    }
}
