package ru.nivanov.SuperIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Ivanov on 26.04.2017.
 */
public class IteratorsConverter implements ConverterOfIterators {
    /**
     * Convert method returns one iterator running over inner iterators.
     * @param it is iterator of iterators.
     * @return result iterator.
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private Iterator<Integer> index = null;

            @Override
            public boolean hasNext() {
                if (it.hasNext()) {
                    return true;
                } else if (!it.hasNext()) {
                    return index.hasNext();
                }
                return false;
            }

            @Override
            public Integer next() throws NoSuchElementException {

                if (index == null) {
                    index = it.next();
                    return index.next();

                } else if (index.hasNext()) {
                    return index.next();

                } else {
                    index = it.next();
                    return index.next();
                }
            }
        };
    }
}
