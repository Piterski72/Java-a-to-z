package ru.nivanov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay Ivanov on 16.06.2017.
 */
class SimpleCache {

    private Map<String, SoftReference<String>> files;

    /**
     * Constructor.
     */
    SimpleCache() {
        this.files = new HashMap<>();
    }

    /**
     * Loads file and returns its content.
     * @param fileName ..
     * @return ..
     */
    public String loadFile(String fileName) {
        String result = "";
        File file = new File(fileName);

        if (files.containsKey(fileName)) {
            result = files.get(fileName).get();

        } else {
            if (file.exists() && file.isFile() && file.canRead()) {

                StringBuilder stringBuilder = new StringBuilder();

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                    String line = br.readLine();

                    while (line != null) {
                        stringBuilder.append(line);
                        stringBuilder.append(System.getProperty("line.separator"));
                        line = br.readLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                result = String.valueOf(stringBuilder);
                SoftReference<String> softResult = new SoftReference<>(result);
                files.put(fileName, softResult);

            }
        }

        return result;

    }

}
