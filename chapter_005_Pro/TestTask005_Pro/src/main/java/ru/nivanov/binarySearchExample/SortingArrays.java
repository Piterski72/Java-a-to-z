package ru.nivanov.binarySearchExample;

/**
 * Created by Nikolay Ivanov on 06.06.2017.
 */
class SortingArrays {

    private final int[] sorted;
    private final int[] unsorted;

    /**
     * Constructor.
     * @param sorted array
     * @param unsorted array
     */
    SortingArrays(int[] sorted, int[] unsorted) {
        this.sorted = sorted;
        this.unsorted = unsorted;
    }

    /**
     * Sorting operation.
     * @return resulting array.
     */
    public int[] sorting() {
        int index;
        int[] result = new int[this.sorted.length + this.unsorted.length];
        System.arraycopy(this.sorted, 0, result, 0, this.sorted.length);
        int lastNotZeroElemIndex = this.sorted.length - 1;

        for (int val : this.unsorted) {
            index = insertPosition(val, result, lastNotZeroElemIndex);
            System.arraycopy(result, index, result, index + 1, result.length - index - 1);
            result[index] = val;
            lastNotZeroElemIndex++;

        }
        return result;
    }

    /**
     * Detecting insertion position.
     * @param value ..
     * @param array ..
     * @param last ..
     * @return position
     */
    private int insertPosition(int value, int[] array, int last) {
        int middle;
        int low = 0;
        int high = last;

        while (low <= high) {

            middle = (high + low) >> 1;

            if (value > array[middle]) {
                low = middle + 1;

            } else if (value < array[middle]) {
                high = middle - 1;
            } else {
                return middle;

            }
        }

        return low;
    }
}
