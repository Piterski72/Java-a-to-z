package ru.nivanov;

import java.util.ArrayList;
import java.util.Iterator;
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
            for (int j = 0; j < array[i].length; j++) {
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
        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        for (int i = 0; i < rows; i++) {
            assert result != null;
            for (int j = 0; j < result[0].length; j++) {
                if (count < list.size() && iterator.hasNext()) {
                    result[i][j] = iterator.next();
                }
                count++;
            }
        }
        return result;
    }

    /**
     * Converting list of massives into global list.
     * @param list ..
     * @return ..
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        Iterator<int[]> iterator = list.iterator();
        int[] current;
        while (iterator.hasNext()) {
            current = iterator.next();
            for (int j = 0; j < current.length; j++) {
                result.add(current[j]);
            }
        }
        return result;
    }
}
