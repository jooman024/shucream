package services.comments;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ActionBean;
import beans.CommentsBean;
import beans.NoteBean;
import beans.UserBean;

public class Comments {
	private HttpServletRequest request;
	private HttpSession session;

	public Comments(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession(); 
	}
	
	public ActionBean backController(int serviceCode) {
		ActionBean action = null;
		if(serviceCode == 50) {
			action = this.CommentsCtl();
		}
		return action;
	}
	
	private ActionBean CommentsCtl() {
		ActionBean action = new ActionBean();
		String page = "RegistPage.jsp", message = null;
		boolean tran = false, access = true;
		
		CommentsBean comments = new CommentsBean();
		ArrayList<CommentsBean> commentsList = new ArrayList<CommentsBean>();
		comments.setComments(this.request.getParameter("comments"));
		commentsList.add(comments);
		
		NoteBean note = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		
		note.setNoteCode(this.request.getParameter("noteCode"));
		note.setCommentsList(commentsList);
		noteList.add(note);
		
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		user.setUserId(this.request.getParameter("UserId"));
		user.setNoteList(noteList);
		
		CommentsDataAccessObject dao = new CommentsDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, false);
		
		if(this.convertToBoolean(dao.insCommentsData(connection, user))) {
			tran = true;
			access = false;
			page = "SellPage.css";
			
			userList = dao.selCommentsData(connection, user);
			
			session.setAttribute("CommentsInfo", user);
		}
		
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, tran);
		dao.closeConnection(connection);
		
		action.setPage(page);
		action.setRedirect(access);
		
		return action;
	}
	
	private boolean convertToBoolean(int value) {
		return value>0? true:false;
	}
}
