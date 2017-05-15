package ru.nivanov.apartments;

/**
 * Created by Nikolay Ivanov on 12.05.2017.
 * @param <T> ..
 * @param <V> ..
 */

public class BaseInfo<T, V> {
    private static final int THIRTY_ONE = 31;
    private static final int SEVENTEEN = 17;
    private final int hash;
    private final T key;
    private V value;

    /**
     * Constructor.
     * @param key ..
     * @param value ..
     */
    public BaseInfo(int hash, T key, V value) {
        this.key = key;
        this.value = value;
        this.hash = hash;

    }

    /**
     * Key getter.
     * @return ..
     */
    public T getKey() {
        return key;
    }

    /**
     * Value getter.
     * @return ..
     */
    public V getValue() {
        return value;
    }

    /**
     * New value setter.
     * @param value ..
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Hash getter.
     * @return ..
     */
    public int getHash() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseInfo<?, ?> baseInfo = (BaseInfo<?, ?>) o;

        return key.equals(baseInfo.key) && value.equals(baseInfo.value);
    }

    @Override
    public int hashCode() {
        int result = SEVENTEEN;
        result = THIRTY_ONE * result + key.hashCode();
        result = THIRTY_ONE * result + value.hashCode();
        return result;
    }
}
