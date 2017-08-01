package ru.nivanov.reverseList;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 07.08.2017.
 */
public class ReverseListTest {
    private ReverseList list;
    private ReverseList.Node<Integer> testNode;
    private ReverseList.Node<Integer> result;

    /**
     * Set up list for tests.
     */
    @Before
    public void setUp() {
        list = new ReverseList();

        result = list.createList(testNode, 3);
    }

    /**
     * Test for ccreating list method.
     */
    @Test
    public void whenCreateListThenReturnResult() {
        assertThat(result.getNext().getValue(), is(1));
    }

    /**
     * Test for reversing list method.
     */
    @Test
    public void whenReverseListThenReturnResult() {
        final int three = 3;

        ReverseList.Node<Integer> reversed = list.reverseList(result);

        Integer[] expected = {2, 1, 0};

        Integer[] results = {reversed.getValue(),
                             reversed.getNext().getValue(),
                             reversed.getNext().getNext().getValue()};

        assertThat(results, is(expected));

    }

}