package ru.nivanov.models;

/**
 * Item class.
 * @author nivanov.
 * @since
 * @version
 */
public class Item {
	/**
	*/
	private String id;
	/**
	*/
	private String name;
	/**
	*/
	private String description;
	/**
	*/
	private long create;
	/**
	*/
	private final int numComm = 20;
	/**
	*/
	private Comments[] comments = new Comments[numComm];
	/**
	*/
	private int num = 0;
	/**
	*/
	public Item() {
	}
	/**
	 * Class Constructor.
	 * @param name input parameter
	 * @param description input parameter
	 * @param create input parameter
	 */
	public Item(String name, String description, long create) {
		this.name = name;
		this.description = description;
		this.create = create;
	}
	/**
	 * Getting name.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Setting name.
	 * @param name input param
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getting description.
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Setting description.
	 * @param description input param
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getting create info.
	 * @return create
	 */
	public long getCreate() {
		return this.create;
	}
	/**
	 * Setting create.
	 *@param create input param
	 */
	public void setCreate(long create) {
		this.create = create;
	}
	/**
	 * Getting id.
	 * @return id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Setting id.
	 * @param id input param
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Add new comments.
	 * @param comments input param
	 */
	public void addCommentz(Comments comments) {
		this.comments[num++] = comments;
	}
	/**
	 * Getting comment text.
	 * @return comment text
	 */
	public String[] getComments() {
		String[] commTxt = new String[numComm];
		try {
		for (int i = 0; i < numComm; i++) {
			commTxt[i] = this.comments[i].getBodyComm();
		}
		} catch (NullPointerException ex) {
			System.out.println("comment not found");
		  }
		return commTxt;
	}
}