package ru.nivanov.binaryTree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 24.05.2017.
 */
public class SearchBinaryTreeTest {
    private final int sixteen = 16;
    private final int ten = 10;

    /**
     * Test for add and search.
     */
    @Test
    public void whenAddThenReturnResult() {
        SearchBinaryTree<Integer> underTest = new SearchBinaryTree<>(new BinaryNode<>(sixteen));
        for (int i = 0; i < sixteen * 2; i++) {
            underTest.add(i);
        }

        BinaryNode<Integer> result = underTest.searchByValue(ten);
        assertThat(result.getValue(), is(ten));

    }

}