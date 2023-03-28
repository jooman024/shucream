package services.note;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.CategoriesBean;
import beans.GoodsCateBean;
import beans.NoteBean;
import beans.PlaceBean;
import beans.UserBean;

public class NoteDataAccessObject extends services.DataAccessObject {

	public NoteDataAccessObject() {}
	
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
	
	final ArrayList<UserBean> getMaxNoteCode(Connection connection, ArrayList<UserBean> user) {
		
		String qur = "SELECT NVL(MAX(NT_NTCODE), '000000001')+1 AS MAXNOTECODE FROM TRDBA.NT";
		
		try {
			this.ps = connection.prepareStatement(qur);
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				while (this.rs.next()) {
					user.get(0).getNoteList().get(0).setNoteCode(this.rs.getNString("MAXNOTECODE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	/* Note에 입력된 정보를 가지고오기 위한 메소드 */
	public ArrayList<UserBean> selNote(Connection connection, UserBean user){
		UserBean userbean = null;
		NoteBean note = null;
		CategoriesBean cate = null;
		PlaceBean place = null;
		GoodsCateBean goods = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<GoodsCateBean> goodsList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		ArrayList<UserBean> userList = null;
		
		String query = "SELECT * FROM TRDBA.NT WHERE NT_CSID = ?";
		
		
		System.out.println(user.getUserId());
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, user.getUserId());
			
			this.rs = this.ps.executeQuery();
			
			if(this.rs.isBeforeFirst()) {	
			  
				while(this.rs.next()) {
				
				place = new PlaceBean();
				place.setPlaceBig(this.rs.getNString("placeBig"));
				place.setPlaceSma(this.rs.getNString("placeSma"));
				placeList = new ArrayList<PlaceBean>();
				placeList.add(place);
				
				goods = new GoodsCateBean();
				goods.setGs_Big(this.rs.getNString("goodsBig"));
				goods.setGs_Small(this.rs.getNString("goodsSma"));
				goodsList = new ArrayList<GoodsCateBean>();
				goodsList.add(goods);
				
				note = new NoteBean();
				note.setNoteCode(this.rs.getNString("goodsCode"));
				note.setGoodsName(this.rs.getNString("goodsName"));
				note.setGoodsPrice(this.rs.getNString("goodsPrice"));
				note.setGoodsQuantity(this.rs.getNString("goodsQuantity"));
				note.setGoodsState(this.rs.getNString("goodsState"));
				note.setConntents(this.rs.getNString("conntents"));
				note.setNoteDate(this.rs.getNString("noteDate"));
				note.setNoteState(this.rs.getNString("noteState"));
				note.setNoteIng(this.rs.getNString("noteIng"));
				note.setTranMethod(this.rs.getNString("tranMethod"));
				
				cate = new CategoriesBean();
				cate.setGoodsList(goodsList);
				cate.setPlaceList(placeList);
				cateList = new ArrayList<CategoriesBean>();
				cateList.add(cate);
				
				note.setCateList(cateList);
				noteList = new ArrayList<NoteBean>();
				noteList.add(note);
				
				userbean.setNoteList(noteList);
				userbean = new UserBean();
				userbean.setUserId(this.rs.getNString("userId"));
				userList = new ArrayList<UserBean>();
				userList.add(userbean);
			   }
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		return userList;
	}
	
	
//	/* Note의 내용을 가지고오기위해서 Categories테이블에 있는 분류네임을 얻기위한 메소드 
//	 * 3~4가지의 column값을 도출하기위하여 객체지향화 시키기
//	 * */
//	public NoteBean selCate(Connection connection, UserBean user){
//		NoteBean note = null;
//		ArrayList<CategoriesBean> cateList = null;
//		CategoriesBean cate = null;
//		String sel[] = 
//			{(user.getNoteList().get(0).getNoteState()),(user.getNoteList().get(0).getNoteIng()),
//             (user.getNoteList().get(0).getGoodsState()),(user.getNoteList().get(0).getTranMethod())};
//		
//		String query = "SELECT CG_NAME AS CGNAME, CG_CODE AS CGCODE "
//					 + "FROM TRDBA.CG WHERE CG_CODE = ?";
//		
//		try {
//			this.ps = connection.prepareStatement(query);
//			
//					
//			
//		} catch (SQLException e) {e.printStackTrace();}
//		
//		
//		return note;
//	}
	
	/* Note_Table에 정보를 입력하기위한 메소드 */
	public int insNote(Connection connection, UserBean user) {
		
		int result = 0;
		
		String ins = "INSERT INTO TRDBA.NT(NT_CSID, NT_NTCODE, NT_GOODS, "
				+ "NT_COUNT, NT_PRICE, NT_NOTE, NT_DATE, NT_TRWAY, "
				+ "NT_NOTEST, NT_NOTETRST, NT_GOSTATE, "
				+ "NT_PLBIG, NT_PLSMALL, NT_GSBIG, NT_GSSMALL) "
				+ "VALUES(?,TRDBA.MAXVALNOTECODE.NEXTVAL,?,?,?,?,DEFAULT, "
				+ "?,?,?,?,?,?,?,?)";
		
		try {
			this.ps = connection.prepareStatement(ins);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getGoodsName());
			this.ps.setNString(3, user.getNoteList().get(0).getGoodsQuantity());
			this.ps.setNString(4, user.getNoteList().get(0).getGoodsPrice());
			this.ps.setNString(5, user.getNoteList().get(0).getConntents());
			
			this.ps.setNString(6, user.getNoteList().get(0).getTranMethod());
			this.ps.setNString(7, user.getNoteList().get(0).getNoteState());
			this.ps.setNString(8, user.getNoteList().get(0).getNoteIng());
			this.ps.setNString(9, user.getNoteList().get(0).getGoodsState());
			this.ps.setNString(10, user.getNoteList().get(0).getCateList().get(0).getPlaceList().get(0).getPlaceBig());
			this.ps.setNString(11, user.getNoteList().get(0).getCateList().get(0).getPlaceList().get(0).getPlaceSma());
			this.ps.setNString(12, user.getNoteList().get(0).getCateList().get(0).getGoodsList().get(0).getGs_Big());
			this.ps.setNString(13, user.getNoteList().get(0).getCateList().get(0).getGoodsList().get(0).getGs_Small());
			
			result = this.ps.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		System.out.println(result);
		return result;
	}
	
	/* 게시판을 등록한 후 등록한 글을 확인하기위한 메소드 */
	public ArrayList<UserBean> firstSelect(Connection connection, ArrayList<UserBean> userList){
		NoteBean note = null;
		CategoriesBean cate = null;
		PlaceBean place = null;
		GoodsCateBean goods = null;
		ArrayList<PlaceBean> placeList = null;
		ArrayList<GoodsCateBean> goodsList = null;
		ArrayList<CategoriesBean> cateList = null;
		ArrayList<NoteBean> noteList = null;
		
		
		String query = "SELECT * FROM TRDBA.NT WHERE NT_CSID = ? AND NT_GOODS = ? ";
		
		try {
			this.ps = connection.prepareStatement(query);
			this.ps.setNString(1, userList.get(0).getUserId());
			this.ps.setNString(2, userList.get(0).getNoteList().get(0).getConntents());
			
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				
				place = new PlaceBean();
				place.setPlaceBig(this.rs.getNString("placeBig"));
				place.setPlaceSma(this.rs.getNString("placeSma"));
				placeList = new ArrayList<PlaceBean>();
				placeList.add(place);
				
				goods = new GoodsCateBean();
				goods.setGs_Big(this.rs.getNString("goodsBig"));
				goods.setGs_Small(this.rs.getNString("goodsSma"));
				goodsList = new ArrayList<GoodsCateBean>();
				goodsList.add(goods);
				
				note = new NoteBean();
				note.setNoteCode(this.rs.getNString("noteCode"));
				note.setNoteCode(this.rs.getNString("goodsCode"));
				note.setGoodsName(this.rs.getNString("goodsName"));
				note.setGoodsPrice(this.rs.getNString("goodsPrice"));
				note.setGoodsQuantity(this.rs.getNString("goodsQuantity"));
				note.setGoodsState(this.rs.getNString("goodsState"));
				note.setConntents(this.rs.getNString("conntents"));
				note.setNoteDate(this.rs.getNString("noteDate"));
				note.setNoteState(this.rs.getNString("noteState"));
				note.setNoteIng(this.rs.getNString("noteIng"));
				note.setTranMethod(this.rs.getNString("tranMethod"));
				
				cate = new CategoriesBean();
				cate.setGoodsList(goodsList);
				cate.setPlaceList(placeList);
				cateList = new ArrayList<CategoriesBean>();
				cateList.add(cate);
				
				note.setCateList(cateList);
				noteList = new ArrayList<NoteBean>();
				noteList.add(note);
				
				UserBean user = new UserBean();
				user.setNoteList(noteList);
				user.setUserId(this.rs.getNString("userId"));
				userList = new ArrayList<UserBean>();
				userList.add(user);
				
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		
		
		return userList;
	}
	
	
}