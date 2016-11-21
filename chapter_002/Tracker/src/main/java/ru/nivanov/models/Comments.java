package ru.nivanov.models;

/**
 * Comments class.
 * @author nivanov.
 * @since
 * @version
 */

public class Comments {
	/**
	*/
	private String idComm;
	/**
	*/
	private String bodyComm;
	/**
	 * Class Constructor.
	 * @param id input parameter
	 * @param text input parameter
	 */
	public Comments(String id, String text) {
		this.idComm = id;
		this.bodyComm = text;
		}
	/**
	 * Getting comment id.
	 * @return id
	 */
	public String getIdComm() {
		return this.idComm;
	}
	/**
	 * Setting comment id.
	 * @param idComm input parameter
	 */
	public void setIdComm(String idComm) {
		this.idComm = idComm;
	}
	/**
	 * Getting text of comment.
	 * @return text
	 */
	public String getBodyComm() {
		return this.bodyComm;
	}
	/**
	 * Setting comment text.
	 * @param bodyComm input parameter (text)
	 */
	public void setBodyComm(String bodyComm) {
		this.bodyComm = bodyComm;
	}
}