package services.search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.CategoriesBean;
import beans.GoodsCateBean;
import beans.NoteBean;
import beans.PlaceBean;
import beans.UserBean;
import services.DataAccessObject;

public class SearchDataAccessObject extends DataAccessObject {

	public SearchDataAccessObject() {}
	
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
	
	
	/* 모든 게시물을 간략하게 보여주는 메소드 */
	public ArrayList<UserBean> simpleAll(Connection connection, UserBean user) {
		PlaceBean place = null;
		CategoriesBean cate = null;
		NoteBean note = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		ArrayList<UserBean> userList = null;
		
		String query = "SELECT NT_CSID AS USERID, NT_GOODS AS TITLE, "
				+ "NT_PRICE AS PRICE, NT_NOTEST AS NOTESTATE, "
				+ "CG.CG_NAME AS STATENAME, NT_PLBIG AS NOTECITY "
				+ "FROM TRDBA.NT INNER JOIN TRDBA.CG ON NT.NT_NOTEST = CG.CG_CODE "
				+ "WHERE CG.CG_NAME = ? OR CG.CG_NAME = ?";
		
		/* 직접적으로 문자를 넣어 검색??? */
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, "삽니다");
			this.ps.setNString(2, "팝니다");
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				
				while(this.rs.next()) {
					place = new PlaceBean();
					placeList = new ArrayList<PlaceBean>();
					place.setPlaceBig(this.rs.getNString("NOTECITY"));
					placeList.add(place);
					
					cate = new CategoriesBean();
					cateList = new ArrayList<CategoriesBean>();
					cate.setPlaceList(placeList);
					cate.setCg_Name(this.rs.getNString("STATENAME"));
					cateList.add(cate);
					
					note = new NoteBean();
					noteList = new ArrayList<NoteBean>();
					
					note.setGoodsName(this.rs.getNString("TITLE"));
					note.setGoodsPrice(this.rs.getNString("PRICE"));
					note.setNoteState(this.rs.getNString("NOTESTATE"));
					note.setCateList(cateList);
					
					user.setUserId(this.rs.getNString("USERID"));
					user.setNoteList(noteList);
					
					userList = new ArrayList<UserBean>();
					userList.add(user);
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		return userList;
	}
	
	/* 특정 유저의 게시물을 검색 */
	public ArrayList<UserBean> simpleUser(Connection connection, UserBean user) {
		PlaceBean place = null;
		CategoriesBean cate = null;
		NoteBean note = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		ArrayList<UserBean> userList = null;
		
		String query = "SELECT NT_CSID AS USERID, NT_GOODS AS TITLE, "
				+ "NT_PRICE AS PRICE, NT_NOTEST AS NOTESTATE, "
				+ "CG.CG_NAME AS STATENAME, NT_PLBIG AS NOTECITY "
				+ "FROM TRDBA.NT INNER JOIN TRDBA.CG ON NT.NT_NOTEST = CG.CG_CODE "
				+ "WHERE NT_CSID = ?";
		
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, user.getUserId());
	
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				
				while(this.rs.next()) {
					place = new PlaceBean();
					placeList = new ArrayList<PlaceBean>();
					place.setPlaceBig(this.rs.getNString("NOTECITY"));
					placeList.add(place);
					
					cate = new CategoriesBean();
					cateList = new ArrayList<CategoriesBean>();
					cate.setPlaceList(placeList);
					cate.setCg_Name(this.rs.getNString("STATENAME"));
					cateList.add(cate);
					
					note = new NoteBean();
					noteList = new ArrayList<NoteBean>();
					
					note.setGoodsName(this.rs.getNString("TITLE"));
					note.setGoodsPrice(this.rs.getNString("PRICE"));
					note.setNoteState(this.rs.getNString("NOTESTATE"));
					note.setCateList(cateList);
					
					user.setUserId(this.rs.getNString("USERID"));
					user.setNoteList(noteList);
					userList = new ArrayList<UserBean>();
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
	
		return userList;
	}
		
	/* 삽니다 // 팝니다 를 선택하여 보여 줄 수 있는 메소드 */
	public ArrayList<UserBean> simpleMenu(Connection connection, UserBean user) {
		PlaceBean place = null;
		CategoriesBean cate = null;
		NoteBean note = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		ArrayList<UserBean> userList = null;
		
		/* 검색을 통한 간략한 정보로 게시물을 표시하고 선택된 게시물을 자세하게 표시 
		 * 유저 아이디, 상품명(제목 타이틀), 상품가격, 게시글 상태코드(삽니다 \\ 팝니다)
		 * 게시글 상태이름, 거래도시
		 * */
		
		/* CG_NAME = 삽니다 OR 팝니다 */
		String query = "SELECT NT_CSID AS USERID, NT_GOODS AS TITLE, "
				     + "NT_PRICE AS PRICE, NT_NOTEST AS NOTESTATE, "
				     + "CG.CG_NAME AS STATENAME, NT_PLBIG AS NOTECITY "
				     + "FROM TRDBA.NT INNER JOIN TRDBA.CG ON NT.NT_NOTEST = CG.CG_CODE "
				     + "WHERE CG.CG_NAME = ?";
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, user.getNoteList().get(0).getCateList().get(0).getCg_Name());
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				
				while(this.rs.next()) {
					place = new PlaceBean();
					placeList = new ArrayList<PlaceBean>();
					place.setPlaceBig(this.rs.getNString("NOTECITY"));
					placeList.add(place);
					
					cate = new CategoriesBean();
					cateList = new ArrayList<CategoriesBean>();
					cate.setPlaceList(placeList);
					cate.setCg_Name(this.rs.getNString("STATENAME"));
					cateList.add(cate);
					
					note = new NoteBean();
					noteList = new ArrayList<NoteBean>();
					
					note.setGoodsName(this.rs.getNString("TITLE"));
					note.setGoodsPrice(this.rs.getNString("PRICE"));
					note.setNoteState(this.rs.getNString("NOTESTATE"));
					note.setCateList(cateList);
					
					user.setUserId(this.rs.getNString("USERID"));
					user.setNoteList(noteList);
					userList = new ArrayList<UserBean>();
				}
			}
		} catch (SQLException e) {e.printStackTrace();}

		return userList;
	}
	
	/* 특정한 내용이 들어있는 게시물의 정보를 요약하여 보여주기위한
	 * 
	 * 게시판에서 간략하게 게시물의 정보를 보여주기 위해 sel을 위한 메소드 */
	public ArrayList<UserBean> simpleChoice(Connection connection, UserBean user) {
		PlaceBean place = null;
		CategoriesBean cate = null;
		NoteBean note = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		ArrayList<UserBean> userList = null;
		
		/* 검색을 통한 간략한 정보로 게시물을 표시하고 선택된 게시물을 자세하게 표시 
		 * 유저 아이디, 상품명(제목 타이틀), 상품가격, 게시글 상태코드(삽니다 \\ 팝니다)
		 * 게시글 상태이름, 거래도시
		 * */
		System.out.println(user.getUserSearch());
		String query = "SELECT NT_CSID AS USERID, NT_GOODS AS TITLE, "
					 + "NT_PRICE AS PRICE, NT_NOTEST AS NOTESTATE, "
				     + "CG.CG_NAME AS STATENAME, NT_PLBIG AS NOTECITY "
				     + "FROM TRDBA.NT INNER JOIN TRDBA.CG ON NT.NT_NOTEST = CG.CG_CODE "
				     + "WHERE NT_GOODS LIKE ? OR NT_NOTE LIKE ?";
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, "%"+user.getUserSearch()+"%");
			this.ps.setNString(2, "%"+user.getUserSearch()+"%");
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				
				while(this.rs.next()) {
					place = new PlaceBean();
					placeList = new ArrayList<PlaceBean>();
					place.setPlaceBig(this.rs.getNString("NOTECITY"));
					placeList.add(place);
					
					cate = new CategoriesBean();
					cateList = new ArrayList<CategoriesBean>();
					cate.setPlaceList(placeList);
					cate.setCg_Name(this.rs.getNString("STATENAME"));
					cateList.add(cate);
					
					note = new NoteBean();
					noteList = new ArrayList<NoteBean>();
					
					note.setGoodsName(this.rs.getNString("TITLE"));
					note.setGoodsPrice(this.rs.getNString("PRICE"));
					note.setNoteState(this.rs.getNString("NOTESTATE"));
					note.setCateList(cateList);
					
					user = new UserBean();
					user.setUserId(this.rs.getNString("USERID"));
					user.setNoteList(noteList);
					userList = new ArrayList<UserBean>();
				}
				
			}else userList = null;
			
		} catch (SQLException e) {e.printStackTrace();}
			
		return userList;
	}
	
}
