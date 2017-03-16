package ru.nivanov;

/**
 * Programm creates sorted massive from two sorted massives.
 * @author nivanov.
 */

class PersonalTask {
    /**
     */
    private final int[] first;
    /**
     */
    private final int[] second;

    /**
     * Class Constructor.
     * @param pfirst first massive
     * @param psecond second massive
     */
    PersonalTask(int[] pfirst, int[] psecond) {
        this.first = pfirst;
        this.second = psecond;
    }

    /**
     * @return result
     */
    int[] sortedMass() {
        int[] result = new int[this.first.length + this.second.length];
        int indexFirst;
        int indexSecond;
        int count = 0;
        int naibol = Math.max(first[this.first.length - 1], second[this.second.length - 1]);
        int[] tempMass = new int[naibol + 1];
        for (int i = 0; i < (this.first.length); i++) {
            for (int j = 0; j < (this.second.length); j++) {
                indexFirst = this.first[i];
                tempMass[indexFirst] = indexFirst;
                indexSecond = this.second[j];
                tempMass[indexSecond] = indexSecond;
            }
        }
        for (int k = 0; k < (naibol + 1); k++) {
            if (tempMass[k] != 0) {
                result[count] = tempMass[k];
                count++;
            }
        }
        return result;
    }
}