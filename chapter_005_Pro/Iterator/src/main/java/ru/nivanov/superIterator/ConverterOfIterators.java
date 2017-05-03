package ru.nivanov.superIterator;

import java.util.Iterator;

/**
 * Created by Nikolay Ivanov on 26.04.2017.
 */
interface ConverterOfIterators {
    /**
     * Convert method returns one iterator running over inner iterators.
     * @param it is iterator of iterators.
     * @return result iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
