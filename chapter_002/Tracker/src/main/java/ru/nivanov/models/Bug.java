package ru.nivanov.models;

/**
 * Bug class.
 * @author nivanov.
 * @since
 * @version
 */
public class Bug extends Item {
	/**
	 * Class Constructor.
	 * @param name input parameter
	 * @param description input parameter
	 * @param create input parameter
	 */
	public Bug(String name, String description, long create) {
		setName(name);
		setDescription(description);
		setCreate(create);
	}
}