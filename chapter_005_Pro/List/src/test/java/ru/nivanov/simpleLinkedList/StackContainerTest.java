package ru.nivanov.simpleLinkedList;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Nikolay Ivanov on 03.05.2017.
 */
public class StackContainerTest {
    /**
     * Test for push and pop.
     */
    @Test
    public void whenPushAndPopThenReturnResult() {
        StackContainer<String> underTest = new StackContainer<>();
        underTest.push("one");
        underTest.push("two");
        underTest.pop();

        assertThat(underTest.get(0), is("one"));
    }


}