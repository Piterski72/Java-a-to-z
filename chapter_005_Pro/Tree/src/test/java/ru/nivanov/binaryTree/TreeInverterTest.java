package ru.nivanov.binaryTree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 20.06.2017.
 */
public class TreeInverterTest {
    private final int three = 3;
    private final int four = 4;
    private final int five = 5;
    private final int six = 6;
    private final int seven = 7;

    /**
     * Test for DFS method.
     */
    @Test
    public void whenInvertTreeDfsThenReturnResult() {
        SearchBinaryTree<Integer> underTest = new SearchBinaryTree<>(new BinaryNode<>(four));
        underTest.add(2);
        underTest.add(six);
        underTest.add(1);
        underTest.add(three);
        underTest.add(five);
        underTest.add(seven);

        TreeInverter<Integer> treeInverter = new TreeInverter<>(underTest);
        treeInverter.invertTreeDfs(underTest.getRoot());

        int[] results = {underTest.getRoot().getLeftChild().getValue(), underTest.getRoot().getRightChild().getValue()};
        int[] expected = {six, 2};
        assertThat(results, is(expected));

    }

    /**
     * Test for BFS method.
     */
    @Test
    public void whenInvertTreeBfsThenReturnResult() {
        SearchBinaryTree<Integer> underTest = new SearchBinaryTree<>(new BinaryNode<>(four));
        underTest.add(2);
        underTest.add(six);
        underTest.add(1);
        underTest.add(three);
        underTest.add(five);
        underTest.add(seven);

        TreeInverter<Integer> treeInverter = new TreeInverter<>(underTest);
        treeInverter.invertTreeBfs(underTest.getRoot());


        int[] results = {underTest.getRoot().getLeftChild().getLeftChild().getValue(),
                         underTest.getRoot().getRightChild().getRightChild().getValue()};
        int[] expected = {seven, 1};
        assertThat(results, is(expected));


    }

}