package ru.nivanov.cycleDetector;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 04.05.2017.
 */
public class CycleDetectorTest {

    private final int three = 3;
    private final int four = 4;

    /**
     * Test for cycle detection.
     */
    @Test
    public void whenHasCycleReturnTrue() {

        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(three);
        Node<Integer> forth = new Node<>(four);

        first.setNext(second);
        second.setNext(third);
        third.setNext(forth);
        forth.setNext(first);

        CycleDetector<Integer> underTest = new CycleDetector<>();
        boolean result = underTest.hasCycle(first);
        assertThat(result, is(true));
    }

}