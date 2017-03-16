package ru.nivanov;

/**
 * Programm sorts values by bubble method.
 * @author nivanov.
 */
class BubbleSortMass {
    /**
     * sorting values by bubble method.
     * @param pvals ..
     */
    public BubbleSortMass(int[] pvals) {
        int[] values = pvals;
    }

    /**
     * sorting values by bubble method.
     * @param pvalues ..
     */
    public void bubbleSort(int[] pvalues) {
        for (int i = pvalues.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (pvalues[j] > pvalues[j + 1]) {
                    int change = pvalues[j];
                    pvalues[j] = pvalues[j + 1];
                    pvalues[j + 1] = change;
                }
            }
        }
    }
}