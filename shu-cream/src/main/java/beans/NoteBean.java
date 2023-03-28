package beans;

import java.util.ArrayList;

public class NoteBean {
	
	private String noteCode;
	private String goodsName;
	private String goodsQuantity;
	private String goodsPrice;
	private String conntents;
	private String noteDate;
	private String tranPlace; // 상세 거래장소
	private String tranMethod; //거래방법 (직거래, 택배거래)
	private String noteState; // 삽니다/팝니다
	private String noteIng; //판매중/판매완료...
	private String goodsState; //새삥, 보통, 빈티지
	private String noteImage;
	private ArrayList<CategoriesBean> cateList;
	private ArrayList<JJimBean> JJimList;
	private ArrayList<CommentsBean> commentsList;
	
	public ArrayList<JJimBean> getJJimList() {
		return JJimList;
	}
	public void setJJimList(ArrayList<JJimBean> jJimList) {
		JJimList = jJimList;
	}
	public ArrayList<CommentsBean> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(ArrayList<CommentsBean> commentsList) {
		this.commentsList = commentsList;
	}
	public ArrayList<CategoriesBean> getCateList() {
		return cateList;
	}
	public void setCateList(ArrayList<CategoriesBean> cateList) {
		this.cateList = cateList;
	}
	private ArrayList<JJimBean> jjim;
	
	public String getNoteCode() {
		return noteCode;
	}
	public void setNoteCode(String noteCode) {
		this.noteCode = noteCode;
	}
	
	public ArrayList<JJimBean> getJjim() {
		return jjim;
	}
	public void setJjim(ArrayList<JJimBean> jjim) {
		this.jjim = jjim;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getConntents() {
		return conntents;
	}
	public void setConntents(String conntents) {
		this.conntents = conntents;
	}
	public String getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}
	public String getTranPlace() {
		return tranPlace;
	}
	public void setTranPlace(String tranPlace) {
		this.tranPlace = tranPlace;
	}
	public String getTranMethod() {
		return tranMethod;
	}
	public void setTranMethod(String tranMethod) {
		this.tranMethod = tranMethod;
	}
	public String getNoteState() {
		return noteState;
	}
	public void setNoteState(String noteState) {
		this.noteState = noteState;
	}
	public String getNoteIng() {
		return noteIng;
	}
	public void setNoteIng(String noteIng) {
		this.noteIng = noteIng;
	}
	public String getGoodsState() {
		return goodsState;
	}
	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
	public String getNoteImage() {
		return noteImage;
	}
	public void setNoteImage(String noteImage) {
		this.noteImage = noteImage;
	}

}
