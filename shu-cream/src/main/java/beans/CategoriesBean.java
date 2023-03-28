package beans;

import java.util.ArrayList;

public class CategoriesBean {
	
	private String cg_Code;
	private String cg_Name;
	
	private ArrayList<PlaceBean> placeList;
	private ArrayList<GoodsCateBean> goodsList;
	
	
	public String getCg_Code() {
		return cg_Code;
	}
	public void setCg_Code(String cg_Code) {
		this.cg_Code = cg_Code;
	}
	public String getCg_Name() {
		return cg_Name;
	}
	public void setCg_Name(String cg_Name) {
		this.cg_Name = cg_Name;
	}
	public ArrayList<PlaceBean> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(ArrayList<PlaceBean> placeList) {
		this.placeList = placeList;
	}
	public ArrayList<GoodsCateBean> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<GoodsCateBean> goodsList) {
		this.goodsList = goodsList;
	}

}
