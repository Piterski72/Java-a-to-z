package ru.nivanov.start;
/**
 * UserAction interface.
 * @author nivanov.
 * @since
 * @version
 */
 public interface UserAction {
	 /**
	 * Menu selection.
	 * @return key..
	 *
	 */
	 int key();
	 /**
	 * Menu acton.
	 * @param input ..
	 * @param tracker ..
	 */
	 void execute(Input input, Tracker tracker);
	 /**
	 * Menu info.
	 * @return info..
	 */
	 String info();
}