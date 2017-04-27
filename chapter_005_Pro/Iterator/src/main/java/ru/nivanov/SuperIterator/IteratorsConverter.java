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
            public Integer next() {
                Integer result;
                if (index == null) {
                    index = it.next();
                    result = index.next();
                    return result;

                }
                if (index.hasNext()) {
                    result = index.next();
                    return result;
                }
                try {
                    index = it.next();
                    result = index.next();
                    return result;
                } catch (NoSuchElementException nsee) {
                    nsee.printStackTrace();
                }
                throw new NoSuchElementException();
            }
        };
    }
}
