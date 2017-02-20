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
	private final int numComm = 20;
	/**
	 */
	private final String[] comments = new String[numComm];
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
	private int num = 0;
	/**
	*/
	private int countCom = 0;
	/**
	*/
	Item() {
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
	* @return number of non empty comments
	*/
	public int getCountCom() {
		return this.countCom;
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
	void setCreate(long create) {
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
	public void addCommentz(String comments) {
		this.comments[num++] = comments;
		countCom++;
	}
	/**
	 * Getting comment text.
	 * @return commResult massive
	 */
	public String[] getComments() {
		String[] commResult = new String[this.countCom];
		for (int i = 0; i < countCom; i++) {
			commResult[i] = this.comments[i];
			}
		return commResult;
	}
}