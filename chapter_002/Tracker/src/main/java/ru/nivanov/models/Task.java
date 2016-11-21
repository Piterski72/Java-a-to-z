package ru.nivanov.models;

/**
 * Task class.
 * @author nivanov.
 * @since
 * @version
 */


public class Task extends Item {
	/**
	 * Class Constructor.
	 * @param name input parameter
	 * @param desc input parameter (text)
	 */
	public Task(String name, String desc) {
		setName(name);
		setDescription(desc);
	}
}