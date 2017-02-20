package ru.nivanov.start;

import ru.nivanov.models.Item;
import ru.nivanov.models.Task;
/**
 * EditItem outer class.
 * @author nivanov.
 * @since
 * @version
 */
class EditItem extends BaseAction {
	EditItem(String name) {
		super(name);
	}
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
}
/**
 * MenuTracker class.
 * @author nivanov.
 * @since
 * @version
 */
 public class MenuTracker {
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private final Input input;
	private final Tracker tracker;
	 private final int leng = 6;
	private final UserAction[] actions = new UserAction[leng];
		public MenuTracker(Input input, Tracker tracker) {
			this.input = input;
			this.tracker = tracker;
		}
	/**
	 * range create.
	 * @return range
	 */
		public int[] getRange() {
			int[] ranges = new int[leng];
			for (int i = 0; i < leng; i++) {
			ranges[i] = i;
		}
		return ranges;
		}
	/**
	 * Build menu realization.
	 *
	 */
		public void fillActions() {
			actions[0] = this.new AddItem("Add new task");
			actions[1] = new MenuTracker.ShowItems("Show all tasks");
			actions[2] = new EditItem("Edit item");
			actions[THREE] = this.new DelItem("Delete task");
			actions[FOUR] = this.new FilterItems("Set filer by name/desc");
			actions[FIVE] = this.new AddItemComments("Add task`s comments");
		}
	/**
	 * Select menu realization.
	 * @param key ..
	 */
		public void select(int key) {
			try {
				this.actions[key].execute(this.input, this.tracker);
				} catch (NullPointerException npe) {
					System.out.println("Incorrect data, please try again");
				}
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
 * ShowItems inner static class.
 * @author nivanov.
 * @since
 * @version
 */
		private static class ShowItems extends BaseAction {
			ShowItems(String name) {
				super(name);
			}
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
		}

	/**
	 * AddItem inner class.
	 * @author nivanov.
	 */
	private class AddItem extends BaseAction {
		AddItem(String name) {
			super(name);
		}

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
		 */
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Enter task`s name: ");
			String desc = input.ask("Enter task`s desc: ");
			tracker.add(new Task(name, desc));
		}
	}

	/**
	 * DelItem inner class.
	 * @author nivanov.
	 * @since
	 * @version
	 */
	private class DelItem extends BaseAction {
		DelItem(String name) {
			super(name);
		}

		/**
		 * key method.
		 * @return key..
		 */
		public int key() {
			return THREE;
		}

		/**
		 * Executing delete action.
		 * @param input ..
		 * @param tracker ..
		 *
		 */
		public void execute(Input input, Tracker tracker) {
			String itemId = input.ask("Enter Id: ");
			int before = tracker.getPosition();
			tracker.remove(itemId);
			if (before == tracker.getPosition()) {
				System.out.println("Wrong id! Deletion imcomplete!");
			}
		}
	}
		/**
 * FilterItems inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class FilterItems extends BaseAction {
			FilterItems(String name) {
				super(name);
			}
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return FOUR;
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
		}
		/**
 * AddItemComments inner class.
 * @author nivanov.
 * @since
 * @version
 */
		private class AddItemComments extends BaseAction {
			AddItemComments(String name) {
				super(name);
			}
	/**
	 * key method.
	 * @return key..
	 */
			public int key() {
				return FIVE;
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
		}
}