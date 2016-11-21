package ru.nivanov.start;
import ru.nivanov.models.*;

/**
 * StartUI class.
 * @author nivanov.
 * @since
 * @version
 */

public class StartUI {
	/**
	* main method.
	* @param args input param
	*/
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Task("second task", "second desc"));
		tracker.add(new Task("third task", "third desc"));
		tracker.add(new Task("third task", "Super third desc"));
		tracker.add(new Task("forth task", "forth desc"));
		tracker.add(new Task("fith task", "fith desc"));
		for (Item item : tracker.getAll()) {
			System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
		}
		System.out.println();
		try {
			String isFoundName = tracker.findByName("third task").getName();
			System.out.println(isFoundName);
			} catch (NullPointerException ex) {
			System.out.println("Name not found");
		}
		try {
			String isFoundDesc = tracker.findByDesc("fith desc").getDescription();
			System.out.println(isFoundDesc);
			} catch (NullPointerException ex) {
			System.out.println("Desc not found");
		}
	}
}