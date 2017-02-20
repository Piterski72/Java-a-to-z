package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 20.02.2017.
 */
class Param {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private final String[] value;

    /**
     * Constructor for param.
     * @param value is input data from command line
     */
    Param(String[] value) {
        this.value = value;
    }

    /**
     * Get dir name.
     * @return directory name
     */
    String getDirName() {
        return this.value[1];
    }

    /**
     * Get file name or mask.
     * @return ..
     */
    String getFileName() {
        return this.value[THREE];
    }

    /**
     * Get log file name.
     * @return ..
     */
    String getLogName() {
        return this.value[SIX];
    }

    /**
     * Get -d key.
     * @return ..
     */
    String getDparam() {
        return this.value[0];
    }

    /**
     * Get -n key.
     * @return ..
     */
    String getNparam() {
        return this.value[2];
    }

    /**
     * Get -m or -f or -r key.
     * @return ..
     */
    String getMFRparam() {
        return this.value[FOUR];
    }

    /**
     * Get -o key.
     * @return ..
     */
    String getOparam() {
        return this.value[FIVE];
    }

    /**
     * Get all values.
     * @return ..
     */
    private String[] getAllparams() {
        return this.value;
    }
}
