package selectMeal.dto;

import java.util.List;
import java.util.ArrayList;

public class Restaurant /* implements Serializable */ {
	private int res_id; // 식당 인덱스
	private String resName; // 식당 이름
	private String pNumber; // 식당 번호
	private String address; // 식당 주소
	private List<ResMenu> menuList = new ArrayList<ResMenu>(); // 식당 메뉴 목록

	public Restaurant() {
	}

	public Restaurant(String resName, String pNumber, String address) {
		this.resName = resName;
		this.pNumber = pNumber;
		this.address = address;
	}

	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ResMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<ResMenu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "[" + res_id + "]" + "식당명: " + resName + ", 전화번호: " + pNumber + ", 주소: " + address;
	}

}
