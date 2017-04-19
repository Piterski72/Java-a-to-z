package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 18.04.2017.
 */
class Account {
    private double value;
    private int requisites;

    /**
     * Constructor.
     * @param value ..
     * @param requisites ..
     */
    Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Value getter.
     * @return ..
     */
    public double getValue() {
        return value;
    }

    /**
     * Value setter.
     * @param value ..
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Requisites getter.
     * @return ..
     */
    public int getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return requisites == account.requisites;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.requisites).hashCode();
    }
}
