package selectMeal.dto;

public class Menu /* implements Serializable */ {
	protected String menu_name;
	protected String category;

	public Menu() {
	}

	public Menu(String menu_name, String category) {
		this.menu_name = menu_name;
		this.category = category;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "음식명: " + menu_name + ", 종류: " + category;
	}
}
