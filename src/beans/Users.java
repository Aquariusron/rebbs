package beans;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String stop;
	private Date updateAt;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getLoginId() {
//		return loginId;
//	}
//
//	public void setLoginId(String loginId) {
//		this.loginId = loginId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public int getBranchId() {
//		return branchId;
//	}
//
//	public void setBranchId(int branchId) {
//		this.branchId = branchId;
//	}
//
//	public int getPostId() {
//		return postId;
//	}
//
//	public void setPostId(int postId) {
//		this.postId = postId;
//	}
//
//	public Date getInsertAt() {
//		return insertAt;
//	}
//
//	public void setInsertAt(Date insertAt) {
//		this.insertAt = insertAt;
//	}
//
	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}
