package ru.nivanov.start;

import org.junit.Test;
import ru.nivanov.models.Item;
import ru.nivanov.models.Task;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tracker set of tests.
 * @author nivanov.
 */

public class TrackerTest {
    /**
     * Add item test .
     */
    @Test
    public void whenAddItemThenReturnPosition() {
        Tracker tracker = new Tracker();
        Item test1 = new Task("first task", "first desc");
        Item test2 = new Task("2 task", "2 desc");
        tracker.add(test1);
        tracker.add(test2);
        assertThat(tracker.getAll().size(), is(2));
    }

    /**
     * Add items comment test .
     */
    @Test
    public void whenAddCommentThenCommentedHaveSameId() {
        Tracker tracker = new Tracker();
        Item test3 = new Task("third task", "third desc");
        tracker.add(test3);
        String testId = test3.getId();
        Item commented = tracker.addComment(testId, "comment one");
        assertThat(testId, is(commented.getId()));
    }

    /**
     * Edit2 items fields test (new item, same id).
     */
    @Test
    public void whenEditTwoItemThenReturnEdited() {
        Tracker tracker = new Tracker();
        Item newtest1 = new Task("first task", "first desc");
        tracker.add(newtest1);
        String testId = newtest1.getId();
        tracker.ieditTwo(testId, "name-ed", "desc-ed");
        assertThat(newtest1.getName(), is("name-ed"));
    }

    /**
     * Remove item test.
     */
    @Test
    public void whenRemoveItemThenReturnResult() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Task("first task", "first desc"));
        Item two = tracker.add(new Task("second task", "second desc"));
        tracker.remove(one.getId());
        List<Item> temp = tracker.getAll();
        assertThat(temp.size(), is(1));
    }

    /**
     * Search by id test.
     */
    @Test
    public void whenFoundByIdThenReturnOK() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("first task", "first desc"));
        tracker.add(new Task("second task", "second desc"));
        tracker.add(new Task("third task", "third desc"));
        List<Item> temp = tracker.getAll();
        String testId = temp.get(2).getId();
        Item found = tracker.findById(testId);
        assertThat(found, is(temp.get(2)));
    }

    /**
     * Search by name test.
     */
    @Test
    public void whenFoundByNameThenReturnResult() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("first task", "first desc"));
        tracker.add(new Task("second task", "second desc"));
        List<Item> temp = tracker.getAll();
        Item testN = tracker.findByName("second task");
        String testId = testN.getId();
        assertThat(testId, is(temp.get(1).getId()));
    }

    /**
     * Search by decription test.
     */
    @Test
    public void whenFoundByDescThenReturnResult() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("first task", "first desc"));
        tracker.add(new Task("second task", "second desc"));
        List<Item> temp = tracker.getAll();
        Item testN = tracker.findByDesc("second desc");
        String testId = testN.getId();
        assertThat(testId, is(temp.get(1).getId()));
    }

    /**
     * Return filtered list (by name).
     */
    @Test
    public void whenFilterByNameThenReturnCount() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("first task", "first desc"));
        tracker.add(new Task("second task", "second desc"));
        tracker.add(new Task("first task", "3 desc"));
        tracker.add(new Task("first task", "first desc"));
        tracker.add(new Task("temp task", "temp desc"));
        List<Item> resultN = tracker.getByName("first task");
        int count = 0;
        for (Item aResultN : resultN) {
            if (!aResultN.getName().equals("first task")) {
                count++;
            }
        }
        assertThat(count, is(0));
    }

    /**
     * Return filtered list (by description).
     */
    @Test
    public void whenFilterByDescThenReturnCount() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("first task", "desc"));
        tracker.add(new Task("second task", "newdesc"));
        tracker.add(new Task("third task", "3 desc"));
        tracker.add(new Task("forth task", "newdesc"));
        tracker.add(new Task("fifth task", "newdesc"));
        ArrayList<Item> resultN = tracker.getByDesc("newdesc");
        int count = 0;
        for (Item aResultN : resultN) {
            if (!aResultN.getDescription().equals("newdesc")) {
                count++;
            }
        }
        assertThat(count, is(0));
    }
}