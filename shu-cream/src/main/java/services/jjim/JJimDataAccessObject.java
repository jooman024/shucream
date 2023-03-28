package services.jjim;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.CategoriesBean;
import beans.GoodsCateBean;
import beans.JJimBean;
import beans.NoteBean;
import beans.PlaceBean;
import beans.UserBean;

public class JJimDataAccessObject extends services.DataAccessObject{

	public JJimDataAccessObject() {

	}

	final Connection openConnection() {
		return this.openConnect();
	}

	final Connection openConnection(String userName, String password) {
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
			userList = null;
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
				
				UserBean user = new UserBean();
				user.setNoteList(noteList);
				user.setUserId(this.rs.getNString("userId"));
				userList = new ArrayList<UserBean>();
				userList.add(user);
				
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		
		
		return userList;
	}
	
	final int insJJimData(Connection connection, UserBean user) {
		int result = 0;
		String dml = "INSERT INTO TRDBA.JJ(JJ_CSID, JJ_NTCODE, JJ_GONAME, JJ_DATE, JJ_COUNT, JJ_PRICE, JJ_GOSTATE, JJ_NTSTATE, JJ_JJSTATE) "
				+ "VALUES(?, ?, ?, DEFAULT, ?, ?, ?, ?, ?)";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			this.ps.setNString(3, user.getNoteList().get(0).getJJimList().get(0).getJjimName());
			this.ps.setInt(4, user.getNoteList().get(0).getJJimList().get(0).getJjimCount());
			this.ps.setInt(5, user.getNoteList().get(0).getJJimList().get(0).getJjimPrice());
			this.ps.setNString(6, user.getNoteList().get(0).getJJimList().get(0).getJjimGoodsState());
			this.ps.setNString(7, user.getNoteList().get(0).getJJimList().get(0).getJjimNoteState());
			this.ps.setNString(8, user.getNoteList().get(0).getJJimList().get(0).getJjimState());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	final ArrayList<UserBean> selJJimData(Connection connection, UserBean user) {
		UserBean userBean = null;
		ArrayList<UserBean> userList = null;
		NoteBean noteBean = new NoteBean();
		ArrayList<NoteBean> noteList = new ArrayList<NoteBean>();
		JJimBean jjimBean = new JJimBean();
		ArrayList<JJimBean> jjimList = new ArrayList<JJimBean>();
		String qur = "SELECT * FROM TRDBA.JJ WHERE JJ_CSID = ? ";
		
		try {
			this.ps = connection.prepareStatement(qur);
			this.ps.setNString(1, user.getUserId());
			
			this.rs = this.ps.executeQuery();
			if(this.rs.isBeforeFirst()) {
				userList = new ArrayList<UserBean>();
				while (this.rs.next()) {
					userBean = new UserBean();
					
					jjimBean.setJjimName(this.rs.getNString("GoodsName"));
					jjimBean.setJjimCount(this.rs.getInt("GoodsCount"));
					jjimBean.setJjimPrice(this.rs.getInt("GoodsPrice"));
					jjimBean.setJjimGoodsState(this.rs.getNString("GoodsState"));
					jjimBean.setJjimNoteState(this.rs.getNString("NoteState"));
					jjimBean.setJjimState(this.rs.getNString("JjimState"));
					jjimList.add(jjimBean);
					
					noteBean.setNoteCode(this.rs.getNString("NoteCode"));
					noteBean.setJJimList(jjimList);
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
	
	final int delJJimData(Connection connection, UserBean user) {
		int result = 0;
		String dml = "DELECT FROM TRDBA.JJ WHERE JJ_CSID = ? AND JJ_NTCODE = ?";
		
		try {
			this.ps = connection.prepareStatement(dml);
			this.ps.setNString(1, user.getUserId());
			this.ps.setNString(2, user.getNoteList().get(0).getNoteCode());
			
			result = this.ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
