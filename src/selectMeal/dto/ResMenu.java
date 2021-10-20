package selectMeal.dto;

public class ResMenu /* extends Menu implements Serializable */ {

	private String menu_name;
	private int res_id;
	private int price;
	
	public ResMenu() {
	}

	public ResMenu(String menu_name, int res_id, int price) {
		this.menu_name = menu_name;
		this.res_id = res_id;
		this.price = price;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "식당번호: " + res_id + ", 메뉴: " + menu_name + ", 가격: " + price;
	}
}
