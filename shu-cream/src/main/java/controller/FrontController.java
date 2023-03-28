package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ActionBean;
import services.auth.Auth;
import services.note.Note;
import services.search.SearchCtl;

@WebServlet({"/Join","/Login","/Logout","/searchUser","/searchMenu","/insNote","/selNote","/simpleCho","/simpleAll",
			 "/Issession"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String jobCode = request.getRequestURI().substring(request.getContextPath().length() + 1);
		ActionBean action = null;
		Auth auth;
		
		if(jobCode.equals("selNote")) action = new Note(request).backcontroller(22);
		else if(jobCode.equals("simpleCho")) action = new SearchCtl(request).backcontroller(23);
		else if(jobCode.equals("simpleAll")) action = new SearchCtl(request).backcontroller(24);
		else if (jobCode.equals("searchMenu")) action = new SearchCtl(request).backcontroller(25);
		else if (jobCode.equals("searchUser")) action = new SearchCtl(request).backcontroller(26);
		else if (jobCode.equals("Issession")) action = new Auth(request).backController(0);
		
		
		
		/* action의 전달방식을 결정하며 이동할 페이지를 지정하는 구문 */
		if (action.isRedirect()) {
			response.sendRedirect(action.getPage());
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(action.getPage());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String jobCode = request.getRequestURI().substring(request.getContextPath().length() + 1);
		ActionBean action = null;
		Auth auth;
		
		if(jobCode.equals("Join")) {
			auth = new Auth(request);
			action = auth.backController(1);
		}else if(jobCode.equals("Login")) {
			auth = new Auth(request);
			action = auth.backController(2);
		}else if(jobCode.equals("Logout")) {
			auth = new Auth(request);
			action = auth.backController(3);
		}
		else if (jobCode.equals("insNote")) action = new Note(request).backcontroller(21);
		
		
		/* action의 전달방식을 결정하며 이동할 페이지를 지정하는 구문 */
		if (action.isRedirect()) {
			response.sendRedirect(action.getPage());
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(action.getPage());
			dispatcher.forward(request, response);
			
		}
		
	}
	
}
