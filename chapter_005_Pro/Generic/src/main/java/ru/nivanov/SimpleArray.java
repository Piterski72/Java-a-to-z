package ru.nivanov;

import java.util.Arrays;

/**
 * Created by Nikolay Ivanov on 27.04.2017.
 * @param <E> generic.
 */
class SimpleArray<E> {
    private E[] objects;
    private int index = 0;

    /**
     * Constructor.
     * @param objects ..
     */
    SimpleArray(E[] objects) {
        this.objects = objects;
    }

    /**
     * Getter for objects.
     * @return ..
     */
    E[] getObjects() {
        return this.objects;
    }

    /**
     * Add method.
     * @param value ..
     */
    void add(E value) {
        this.objects[index++] = value;
    }

    /**
     * Get object method.
     * @param position ..
     * @return ..
     */
    E get(int position) {
        return this.objects[position];
    }

    /**
     * Delete method.
     * @param value ..
     */
    void delete(E value) {
        for (int i = 0; i < this.objects.length; i++) {
            if (this.objects[i].equals(value)) {
                System.arraycopy(this.objects, (i + 1), this.objects, i, this.objects.length - i - 1);
                this.objects = Arrays.copyOf(this.objects, this.objects.length - 1);
                break;
            }
        }
    }

    /**
     * Update value in position pos.
     * @param value ..
     * @param pos ..
     */
    void update(E value, int pos) {
        if (pos < this.objects.length) {
            this.objects[pos] = value;
        }
    }


}
