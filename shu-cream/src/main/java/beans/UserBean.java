package beans;

import java.util.ArrayList;

public class UserBean {
	private String userId;
	private String userName;
	private String userPw;
	private String userPhone;
	private String userAddr;
	private String userSearch;
	private ArrayList<LoginBean> logInfo;
	private ArrayList<NoteBean> noteList;
	
	
	public String getUserSearch() {
		return userSearch;
	}
	public void setUserSearch(String userSearch) {
		this.userSearch = userSearch;
	}

	public ArrayList<NoteBean> getNoteList() {
		return noteList;
	}
	public void setNoteList(ArrayList<NoteBean> noteList) {
		this.noteList = noteList;
	}
	public ArrayList<LoginBean> getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(ArrayList<LoginBean> logInfo) {
		this.logInfo = logInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
}
