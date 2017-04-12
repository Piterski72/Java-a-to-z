package ru.nivanov;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolay Ivanov on 12.04.2017.
 */
class ConvertList {

    /**
     * Array to list method.
     * @param array ..
     * @return ..
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                result.add(array[i][j]);
            }
        }
        return result;
    }

    /**
     * List to array method.
     * @param list ..
     * @param rows ..
     * @return ..
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = null;
        if (list.size() > rows && (list.size() % rows) > 0) {
            result = new int[rows][(list.size() / rows + 1)];
        } else if (list.size() > rows && (list.size() % rows == 0)) {
            result = new int[rows][(list.size() / rows)];
        } else if (list.size() == rows) {
            result = new int[rows][rows];
        } else if (list.size() < rows) {
            result = new int[rows][list.size()];
        }
        int count = 0;
        for (int i = 0; i < rows; i++) {
            assert result != null;
            for (int j = 0; j < result[0].length; j++) {
                if (count < list.size()) {
                    result[i][j] = list.get(count);
                } else {
                    result[i][j] = 0;
                }
                count++;
            }
        }
        return result;
    }
}
