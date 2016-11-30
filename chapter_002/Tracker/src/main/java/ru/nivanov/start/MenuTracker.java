package ru.nivanov.start;
import ru.nivanov.models.*;
/**
 * EditItem outer class.
 * @author nivanov.
 * @since
 * @version
 */
class EditItem implements UserAction {
	/**
	 * key method.
	 * @return key..
	 */
	public int key() {
		return 2;
	}
	/**
	 * Executing edit action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				String id = input.ask("Enter task`s id: ");
				String name = input.ask("Enter task`s name: ");
				String desc = input.ask("Enter task`s desc: ");
				tracker.ieditTwo(id, name, desc);
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Edit item");
			}
}
/**
 * MenuTracker class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuTracker {
	 private final int leng = 6;
	 private final int three = 3;
	 private final int four = 4;
	 private final int five = 5;
	 private Input input;
	 private Tracker tracker;
	 private UserAction[] actions = new UserAction[leng];
		public MenuTracker(Input input, Tracker tracker) {
			this.input = input;
			this.tracker = tracker;
		}
	/**
	 * Build menu realization.
	 *
	 */
		public void fillActions() {
			actions[0] = this.new AddItem();
			actions[1] = new MenuTracker.ShowItems();
			actions[2] = new EditItem();
			actions[three] = this.new DelItem();
			actions[four] = this.new FilterItems();
			actions[five] = this.new AddItemComments();
		}
	/**
	 * Select menu realization.
	 * @param key ..
	 */
		public void select(int key) {
			this.actions[key].execute(this.input, this.tracker);
		}
	/**
	 * Show menu realization.
	 *
	 */
		public void show() {
			for (UserAction action : this.actions) {
				if (action != null) {
					System.out.println(action.info());
				}
			}
		}
/**
 * AddItem inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class AddItem implements UserAction {
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return 0;
			}
	/**
	 * Executing add action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				String name = input.ask("Enter task`s name: ");
				String desc = input.ask("Enter task`s desc: ");
				tracker.add(new Task(name, desc));
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Add new item");
			}
		}
/**
 * ShowItems inner static class.
 * @author nivanov.
 * @since
 * @version
 */
		private static class ShowItems implements UserAction {
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return 1;
			}
	/**
	 * Executing list action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				Item[] res = tracker.getAll();
				for (int j = 0; j < res.length; j++) {
					System.out.printf("%s %s %s \n", res[j].getId(), res[j].getName(), res[j].getDescription());
						for (int i = 0; i < res[j].getCountCom(); i++) {
							System.out.println(res[j].getComments()[i]);
						}
				}
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Show all items");
			}
		}
/**
 * DelItem inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class DelItem implements UserAction {
			private final int menuNum = 3;
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return menuNum;
			}
	/**
	 * Executing delete action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				String itemId = input.ask("Enter Id: ");
				tracker.remove(itemId);
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Delete item");
			}
		}
		/**
 * FilterItems inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class FilterItems implements UserAction {
			private final int menuNum = 4;
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return menuNum;
			}
	/**
	 * Executing filtering action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				String cho = input.ask("Choose filtering: name - by name, desc - by description: ");
					if (cho.equals("name")) {
						String name = input.ask("Enter name: ");
						for (Item item : tracker.getByName(name)) {
							System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
						}
					} else if (cho.equals("desc")) {
						String desc = input.ask("Enter description: ");
						for (Item item : tracker.getByDesc(desc)) {
							System.out.printf("%s %s %s \n", item.getId(), item.getName(), item.getDescription());
						}
					} else {
						System.out.println("incorrect choice");
						}
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Set filter");
			}
		}
		/**
 * AddItemComments inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class AddItemComments implements UserAction {
			private final int menuNum = 5;
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return menuNum;
			}
	/**
	 * Executing add comments action.
	 * @param input ..
	 * @param tracker ..
	 *
	 */
			public void execute(Input input, Tracker tracker) {
				String itemId = input.ask("Enter Id: ");
				String comms = input.ask("Enter comment: ");
				tracker.addComment(itemId, comms);
			}
	/**
	 * Info method.
	 * @return info..
	 */
			public String info() {
				return String.format("%d. %s ", this.key(), "Add item`s comment");
			}
		}
}