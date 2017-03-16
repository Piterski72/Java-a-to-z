package ru.nivanov.start;

import org.junit.Test;
import ru.nivanov.models.Item;
import ru.nivanov.models.Task;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * StartUITest class.
 * @author nivanov.
 * @version 1.1
 */

public class StartUITest {
    /**
     * Test for add item.
     */
    @Test
    public void whenAddItemThenReturnId() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "new task", "new desc", "n", "1", "y"});
        new StartUI(input, tracker).init();
        Item newOne = tracker.findByName("new task");
        Item[] result = tracker.getAll();
        assertThat(newOne.getId(), is(result[0].getId()));
    }

    /**
     * Test for edit item.
     */
    @Test
    public void whenEditItemThenReturnId() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("first task", "first desc");
        tracker.add(test1);
        Input input = new StubInput(
                new String[]{"1", "n", "2", test1.getId(), "task ver1.1", "desc ver1.1", "n", "1", "y"});
        new StartUI(input, tracker).init();
        Item editOne = tracker.findByName("task ver1.1");
        assertThat(editOne.getId(), is(test1.getId()));
    }

    /**
     * Test for delete item.
     */
    @Test
    public void whenDeleteItemThenReturnOk() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("first task", "first desc");
        Item test2 = new Task("sec task", "sec desc");
        Item test3 = new Task("tr task", "trc desc");
        tracker.add(test1);
        tracker.add(test2);
        tracker.add(test3);
        Input input = new StubInput(new String[]{"1", "n", "3", test1.getId(), "n", "1", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getPosition(), is(2));
    }

    /**
     * Test for delete incorrect id item.
     */
    @Test
    public void whenDeleteIncorrectIdItemThenNoDelete() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("first task", "first desc");
        Item test2 = new Task("sec task", "sec desc");
        tracker.add(test1);
        tracker.add(test2);
        Input input = new StubInput(new String[]{"1", "n", "3", "test1Id", "n", "1", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getPosition(), is(2));
    }

    /**
     * Test for adding comments to item.
     */
    @Test
    public void whenAddCommentToItemThenReturnIt() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("first task", "first desc");
        tracker.add(test1);
        Input input = new StubInput(new String[]{"1", "n", "5", test1.getId(), "new comment", "n", "1", "y"});
        new StartUI(input, tracker).init();
        Item[] result = tracker.getAll();
        String[] comms = result[0].getComments();
        assertThat(comms[0], is("new comment"));
    }

    /**
     * Test for filter by name.
     */
    @Test
    public void whenFilterByNameThenReturnOk() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("task", "first desc");
        Item test2 = new Task("sec task", "desc");
        Item test3 = new Task("trd task", "desc");
        Item test4 = new Task("forth task", "4 desc");
        Item test5 = new Task("task", "desc");
        tracker.add(test1);
        tracker.add(test2);
        tracker.add(test3);
        tracker.add(test4);
        tracker.add(test5);
        Input input = new StubInput(new String[]{"1", "n", "4", "name", "task", "y"});
        new StartUI(input, tracker).init();
        final int expected = 2;
        assertThat(tracker.getByName("task").length, is(expected));
    }

    /**
     * Test for filter by name.
     */
    @Test
    public void whenFilterByDescThenReturnOk() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("task", "first desc");
        Item test2 = new Task("sec task", "desc33");
        Item test3 = new Task("trd task", "desc33");
        Item test4 = new Task("forth task", "new desc33");
        Item test5 = new Task("task", "desc33");
        tracker.add(test1);
        tracker.add(test2);
        tracker.add(test3);
        tracker.add(test4);
        tracker.add(test5);
        Input input = new StubInput(new String[]{"1", "n", "4", "desc", "desc33", "y"});
        new StartUI(input, tracker).init();
        final int expected = 3;
        assertThat(tracker.getByDesc("desc33").length, is(expected));
    }
}
