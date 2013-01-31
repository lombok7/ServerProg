package dy.beans;

import java.sql.Timestamp;

/**
 * Member Value Object.
 * @author Administrator
 *
 */

public class MemberVO {
	/**
	 * @uml.property  name="id"
	 */
	private String id;
	/**
	 * @uml.property  name="pwd"
	 */
	private String pwd;
	/**
	 * @uml.property  name="filename"
	 */
	private String filename;
	/**
	 * @uml.property  name="filesize"
	 */
	private int filesize;
	
	private Timestamp datetime;
	
	public MemberVO() {};
	
	public MemberVO(String id, String pwd, String filename, int filesize) {
		this.setId(id);
		this.setPwd(pwd);
		this.setFilename(filename);
		this.setFilesize(filesize);
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property  name="pwd"
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 * @uml.property  name="pwd"
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return
	 * @uml.property  name="filename"
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 * @uml.property  name="filename"
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return
	 * @uml.property  name="filesize"
	 */
	public int getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize
	 * @uml.property  name="filesize"
	 */
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	
	public Timestamp getDatetime() {
		return datetime;
	}
	
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
}
