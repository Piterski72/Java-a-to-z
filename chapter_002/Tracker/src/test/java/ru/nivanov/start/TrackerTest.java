package ru.nivanov.start;
import ru.nivanov.models.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tracker set oftests.
 * @author nivanov.
 * @since
 * @version
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
	assertThat(tracker.getPosition(), is(2));
	}
	/**
	* Add items comment test .
	*/
	@Test
	public void whenAddCommentThenCommentedHaveSameId() {
		Tracker tracker = new Tracker();
		Item test3 = new Task("third task", "third desc");
		tracker.add(test3);
		String testId = tracker.findByName("third task").getId();
		Comments comment = new Comments(testId, "comment one");
		Item commented = tracker.addComment(comment);
		assertThat(test3.getId(), is(commented.getId()));
	}
	/**
	* Edit items fields test (search by position).
	*/
	@Test
	public void whenEditOneItemNameThenReturnEdited() {
		Tracker tracker = new Tracker();
		Item test1 = new Task("first task", "first desc");
		Item test2 = new Task("2 task", "2 desc");
		tracker.add(test1);
		tracker.add(test2);
		tracker.ieditOne(1, "secT", "secDsc");
		assertThat(test2.getName(), is("secT"));
	}
	/**
	* Edit2 items fields test (new item, same id).
	*/
	@Test
	public void whenEditTwoItemThenReturnEdited() {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		Item[] temp = tracker.getAll();
		Item newtest1 = new Task("new-first task", "new-first desc");
		newtest1.setId(temp[0].getId());
		tracker.ieditTwo(newtest1);
		Item[] result = tracker.getAll();
		assertThat(result[0].getName(), is("new-first task"));
	}
	/**
	* Remove item test.
	*/
	@Test
	public void whenRemoveItemThenReturnResult() {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Task("second task", "second desc"));
		tracker.remove(1);
		Item[] temp = tracker.getAll();
		assertThat(temp.length, is(1));
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
		Item[] temp = tracker.getAll();
		String testId = temp[2].getId();
		Item found = tracker.findById(testId);
		assertThat(found, is(temp[2]));
	}
	/**
	* Search by name test.
	*/
	@Test
	public void whenFoundByNameThenReturnResult() {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Task("second task", "second desc"));
		Item[] temp = tracker.getAll();
		Item testN = tracker.findByName("second task");
		String testId = testN.getId();
		assertThat(testId, is(temp[1].getId()));
	}
	/**
	* Search by decription test.
	*/
	@Test
	public void whenFoundByDescThenReturnResult() {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Task("second task", "second desc"));
		Item[] temp = tracker.getAll();
		Item testN = tracker.findByDesc("second desc");
		String testId = testN.getId();
		assertThat(testId, is(temp[1].getId()));
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
		Item[] resultN = tracker.getByName("first task");
		int count = 0;
		for (int i = 0; i < resultN.length; i++) {
			if (!resultN[i].getName().equals("first task")) {
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
		Item[] resultN = tracker.getByDesc("newdesc");
		int count = 0;
		for (int i = 0; i < resultN.length; i++) {
			if (!resultN[i].getDescription().equals("newdesc")) {
				count++;
			}
		}
		assertThat(count, is(0));
	}
}