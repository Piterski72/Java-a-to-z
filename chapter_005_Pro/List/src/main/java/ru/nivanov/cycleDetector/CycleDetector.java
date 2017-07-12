package ru.nivanov.cycleDetector;

import ru.nivanov.simpleListThreadSafe.ArrayContainer;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 * @param <T> ..
 */
public class CycleDetector<T> {

    private final ArrayContainer<Node<T>> myContainer = new ArrayContainer<>();

    /**
     * Method for finding cycles in lists.
     * @param first ..
     * @return true if cycle found or false if not.
     */
    public boolean hasCycle(Node<T> first) {
        Node<T> current = first;
        myContainer.add(current);
        while (current.getNext() != null) {
            current = current.getNext();
            for (int i = 0; i < myContainer.getSize(); i++) {
                if (current.equals(myContainer.get(i))) {
                    return true;
                }
            }
            myContainer.add(current);
        }
        return false;
    }

    /**
     * Method for finding cycles in lists.
     * @param first ..
     * @return true if cycle found or false if not.
     */
    public boolean hasCycleNew(Node<T> first) {
        Node<T> currentOne = first.getNext();
        Node<T> currentTwo = currentOne.getNext();
        while (!currentOne.equals(currentTwo)) {
            if (currentOne == null || currentTwo == null) {
                return false;
            }
            currentOne = currentOne.getNext();
            currentTwo = currentTwo.getNext().getNext();
        }
        return true;

    }
}
