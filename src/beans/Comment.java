package beans;

import java.io.Serializable;
import java.util.Date;
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String text;
	private String name;
	private int id;
	private int user_id;
	private int message_id;
	private Date insert_dt;
	private Date update_dt;

//ゲッターとセッターは残す。
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return text;
	}

	public void setComment(String text) {
		this.text = text;
	}

	public Date getInsertDate() {
		return insert_dt;
	}

	public void setInsertDate(Date insert_dt) {
		this.insert_dt = insert_dt;
	}

	public Date getUpdateDate() {
		return update_dt;
	}

	public void setUpdateDate(Date update_dt) {
		this.update_dt = update_dt;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getMessageId() {
		return message_id;
	}

	public void setMessageId(int message_id) {
		this.message_id = message_id;
	}
}