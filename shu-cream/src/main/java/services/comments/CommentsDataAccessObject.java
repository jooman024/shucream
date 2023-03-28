package services.comments;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.CommentsBean;
import beans.JJimBean;
import beans.NoteBean;
import beans.UserBean;

public class CommentsDataAccessObject extends services.DataAccessObject{
	public CommentsDataAccessObject() {}
	final Connection openConnection() {
		return this.openConnect();
	}
	final Connection openConnection(String userName, String password ) {
		return this.openConnect();
	}
	final void closeConnection(Connection connect) {
		this.closeConnect(connect);
	}
	final void modifyTranStatus(Connection connect, boolean tran) {
		this.setTranStatus(connect, tran);
	}
	final void setTransaction(boolean tran, Connection connect) {
		this.setTransactionEnd(tran, connect);
	}
	
	final int insCommentsData(Connection connection, UserBean user) {
		int result = 0;
		String dml = "INSERT INTO TRDBA.CM(CM_CSID, CM_NTCODE, CM_COMMENT) "
				+ "VALUES(?, ?, ?)";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			this.ps.setNString(3, user.getNoteList().get(0).getCommentsList().get(0).getComments());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	final ArrayList<UserBean> selCommentsData(Connection connection, UserBean user) {
		UserBean userBean = null;
		ArrayList<UserBean> userList = null;
		NoteBean noteBean = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		CommentsBean commentsBean = new CommentsBean();
		ArrayList<CommentsBean> commentsList = new ArrayList<CommentsBean>();
		String qur = "SELECT * FROM TRDBA.CM WHERE CM_CSID = ? AND CM_NTCODE = ?";
		
		try {
			this.ps = connection.prepareStatement(qur);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				userList = new ArrayList<UserBean>();
				while (this.rs.next()) {
					userBean = new UserBean();
					
					commentsBean.setComments(this.rs.getNString("Comments"));
					commentsList.add(commentsBean);
					
					noteBean.setNoteCode(this.rs.getNString("NoteCode"));
					noteBean.setCommentsList(commentsList);
					noteList.add(noteBean);
					
					userBean.setUserId(this.rs.getNString("UserId"));
					userBean.setNoteList(noteList);
					userList.add(userBean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
