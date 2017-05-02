package ru.nivanov;

/**
 * Created by Nikolay Ivanov on 27.04.2017.
 * @param <E> generic.
 */
public class SimpleArray<E> {
    private final E[] objects;
    private int index = 0;

    /**
     * Constructor.
     * @param objects ..
     */
    public SimpleArray(E[] objects) {
        this.objects = objects;
    }

    /**
     * Constructor with size.
     * @param size ..
     */
    public SimpleArray(int size) {
        this.objects = (E[]) new Object[size];
    }

    /**
     * Getter for objects.
     * @return ..
     */
    public E[] getObjects() {
        return this.objects;
    }

    /**
     * Add method.
     * @param value ..
     */
    public void add(E value) {
        this.objects[index++] = value;
    }

    /**
     * Get object method.
     * @param position ..
     * @return ..
     */
    public E get(int position) {
        return this.objects[position];
    }

    /**
     * Delete method.
     * @param pos ..
     */
    public void delete(int pos) {
        this.index--;
        for (int i = pos; i < this.objects.length; i++) {
            if ((i + 1) < this.objects.length) {
                this.objects[i] = this.objects[i + 1];
            } else {
                this.objects[i] = null;
            }
        }
    }

    /**
     * Update value in position pos.
     * @param value ..
     * @param pos ..
     */
    public void update(E value, int pos) {
        if (pos < this.objects.length) {
            this.objects[pos] = value;
        }
    }

    /**
     * Size getter.
     * @return ..
     */
    public int getLength() {
        return this.objects.length;
    }
}
