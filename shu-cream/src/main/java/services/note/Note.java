package services.note;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.ActionBean;
import beans.CategoriesBean;
import beans.GoodsCateBean;
import beans.NoteBean;
import beans.PlaceBean;
import beans.UserBean;

public class Note {
	private HttpServletRequest request;
	private HttpSession session;
	
	public Note(HttpServletRequest request){
		this.request = request;
		session = this.request.getSession();
	}
	
	
	public ActionBean backcontroller(int serviceCode) {
		ActionBean action = null;
		
		if(serviceCode == 21) {
			action = this.NoteInsCtl();
		}else if(serviceCode == 22) {
			action = this.NoteSelCtl();   
		}
		return action;
	}
	
	/* 아이디를 사용하여 게시물을 검색하여 가지고 오기위한 Ctl */
	private ActionBean NoteSelCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		String page = "RegistPage.jsp", message = null;
		boolean access = true;
		
		user.setUserId(this.request.getParameter("UserId"));
		
		NoteDataAccessObject dao = new NoteDataAccessObject();
		Connection connection = dao.openConnection();
		
		if(this.request.getParameter("UserId") != null) {
			
			userList = dao.selNote(connection, user);
			session.setAttribute("Note", userList);
			
			access = false;
			page = "RegistPage.jsp"; 
			
		}else {
			
			access = true;
			page = "index.jsp"; 
			
			message = "Error:입력하신 아이디의 게시물이 존재하지 않습니다..";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
		}
		
		dao.closeConnection(connection);
		
		action.setPage(page+message);
		action.setRedirect(access);
		
		return action;
	}
	
	/* note 테이블에 data를 insert하기위한 메소드 */
	private ActionBean NoteInsCtl() {
		ActionBean action = new ActionBean();
		UserBean user = new UserBean();
		CategoriesBean cate = new CategoriesBean();
		PlaceBean place = new PlaceBean();
		ArrayList<PlaceBean> placeList = new ArrayList<PlaceBean>();
		GoodsCateBean goods = new GoodsCateBean();
		ArrayList<GoodsCateBean> goodsList = new ArrayList<GoodsCateBean>();
		ArrayList<CategoriesBean> cateList = null; 
		ArrayList<UserBean> userList = null;
		NoteBean note = new NoteBean(); 
		String page, message = null;
		boolean tran = false, access = true;
		
		place.setPlaceBig(this.request.getParameter("placeBig"));
		place.setPlaceSma(this.request.getParameter("placeSma"));
		goods.setGs_Big(this.request.getParameter("gs_Big"));
		goods.setGs_Small(this.request.getParameter("gs_Small"));
		
		placeList.add(place);
		goodsList.add(goods);
		cate.setGoodsList(goodsList);
		cate.setPlaceList(placeList);
		cateList = new ArrayList<CategoriesBean>();
		cateList.add(cate);
		note.setCateList(cateList);
		
		note.setGoodsName(this.request.getParameter("goodsName"));
		note.setGoodsQuantity(this.request.getParameter("goodsQuantity"));
		note.setGoodsPrice(this.request.getParameter("goodsPrice"));
		note.setConntents(this.request.getParameter("textarea"));
	
		// 상품상태
		if(this.request.getParameter("goodsState").equals("새삥")) note.setGoodsState("GS1");
		else if(this.request.getParameter("goodsState").equals("보통")) note.setGoodsState("GS2");
		else if(this.request.getParameter("goodsState").equals("빈티지")) note.setGoodsState("GS3");
			
		
		// 판매중 , 판매완료 등
		if(this.request.getParameter("noteIng").equals("판매중")) note.setNoteIng("T01");
	    else if(this.request.getParameter("noteIng").equals("구매중")) note.setNoteIng("T00");
		else if(this.request.getParameter("noteIng").equals("판매완료")) note.setNoteIng("T02");
		else note.setNoteIng("T03");
		
		//삽니다 , 팝니다
		if(this.request.getParameter("noteState").equals("삽니다")) note.setNoteState("T04");
		else note.setNoteState("T05");
		
		//택배 , 직거래
		if(this.request.getParameter("tranMethod").equals("택배")) note.setTranMethod("TM1");
		else note.setTranMethod("TM2");
		note.setNoteImage(this.request.getParameter("noteImage") == null ? null : this.request.getParameter("noteImage"));
		
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		noteList.add(note);
		user.setNoteList(noteList);
		user.setUserId(this.request.getParameter("userId"));
		
		
		NoteDataAccessObject dao = new NoteDataAccessObject();
		Connection connection = dao.openConnection();
		dao.modifyTranStatus(connection, tran);
		
		//dao.getMaxNoteCode(connection, userList);
		
		if(this.convertToBoolean(dao.insNote(connection, user))) {
			System.out.println("NoteInsCtl");
			userList = new ArrayList<UserBean>();
			userList.add(user);
			
			/* 입력한 내용을 client로 보내어 보여주기위한 */
			userList = dao.firstSelect(connection, userList);
			
			
			userList.get(0).getNoteList().get(0).setGoodsState(this.convertToCode(userList.get(0).getNoteList().get(0).getGoodsState()));
			userList.get(0).getNoteList().get(0).setNoteIng(this.convertToCode(userList.get(0).getNoteList().get(0).getNoteIng()));
			userList.get(0).getNoteList().get(0).setNoteState(this.convertToCode(userList.get(0).getNoteList().get(0).getNoteState()));
			userList.get(0).getNoteList().get(0).setTranMethod(this.convertToCode(userList.get(0).getNoteList().get(0).getTranMethod()));
			
			
			tran = true;
			access = false;
			page = "SellPage.jsp";
			message = "등록확인:게시물 등록이 완료되었습니다.";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
			
			request.setAttribute("NoteList", userList);
		
			
		}else {
			page = "RegistPage.jsp";
			message = "Error:게시판의 양식과 다릅니다.";
			
			try {
				message = ("?message=")+URLEncoder.encode(message, "UTF-8");
			} catch (UnsupportedEncodingException e) {e.printStackTrace();}
		}
		
		dao.setTransaction(tran, connection);
		dao.modifyTranStatus(connection, tran);
		dao.closeConnection(connection);
		
		
		action.setPage(page+message);
		action.setRedirect(access);
		
		return action;
	}
	
	private boolean convertToBoolean(int value) {
		return value>0? true:false;
	}
	
	private String convertToCode(String code) {
		String codeName = "";
		if(code.equals("T00")) {
			codeName = "구매중";
		}else if(code.equals("T01")) {
			codeName = "판매중";
		}else if(code.equals("T02")) {
			codeName = "판매완료";
		}else if(code.equals("T03")) {
			codeName = "구입완료";
		}else if(code.equals("T04")) {
			codeName = "삽니다";
		}else if(code.equals("T05")) {
			codeName = "팝니다";
		}else if(code.equals("TM1")) {
			codeName = "택배";
		}else if(code.equals("TM2")) {
			codeName = "직거래";
		}else if(code.equals("GS1")) {
			codeName = "새삥";
		}else if(code.equals("GS2")) {
			codeName = "보통";
		}else if(code.equals("GS3")) {
			codeName = "빈티지";
		}else if(code.equals("JJ1")) {
			codeName = "찜완료";
		}else if(code.equals("JJ2")) {
			codeName = "찜해제";
		}else if(code.equals("구매중")) {
			codeName = "T00";
		}else if(code.equals("판매중")) {
			codeName = "T01";
		}else if(code.equals("판매완료")) {
			codeName = "T02";
		}else if(code.equals("구입완료")) {
			codeName = "T03";
		}else if(code.equals("삽니다")) {
			codeName = "T04";
		}else if(code.equals("팝니다")) {
			codeName = "T05";
		}else if(code.equals("택배")) {
			codeName = "TM1";
		}else if(code.equals("직거래")) {
			codeName = "TM2";
		}else if(code.equals("새삥")) {
			codeName = "GS1";
		}else if(code.equals("보통")) {
			codeName = "GS2";
		}else if(code.equals("빈티지")) {
			codeName = "GS3";
		}else if(code.equals("찜완료")) {
			codeName = "JJ1";
		}else if(code.equals("찜해제")) {
			codeName = "JJ2";
		}
		return codeName;
	}
}