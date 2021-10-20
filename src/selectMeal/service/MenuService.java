package selectMeal.service;

import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.MenuDaoImpl;
import selectMeal.dao.interFace.MenuDao;
import selectMeal.dto.Menu;

public class MenuService {
	private MenuDao menuDao = MenuDaoImpl.getInstance();
	private List<Menu> menus = new ArrayList<Menu>();

	public MenuService() {
		getMenuList();
	}

	// 음식 랜덤 선택기
	public String randomMeal() {
		getMenuList();
		int random = (int) (Math.random() * menus.size());

		return menus.get(random).getMenu_name();

	}

	// 음식 등록하기
	public boolean addMenu(String foodName, int foodCat) {
		for (Menu menu : menus) {
			if (!(menu.getMenu_name()).equals(foodName)) {
				continue;
			} else {
				return false; // 음식 등록 실패
			}
		}
		String fCat = null;
		switch (foodCat) {
		case 1:
			fCat = "한식";
			break;
		case 2:
			fCat = "일식";
			break;
		case 3:
			fCat = "중식";
			break;
		case 4:
			fCat = "양식";
			break;
		default:
			fCat = "기타";
		}
		Menu menu = new Menu(foodName, fCat);
		menuDao.insertMenu(menu);
		menus.add(menu);
		return true; // 음식 등록 완료
	}
	
	// 음식 목록 가져오기
	public void getMenuList() {
		menus = menuDao.selectAllMenu();
	}

	// 음식 목록 출력
	public void printMenuList() {
		for (Menu menu : menus) {
			System.out.println(menu);
		}
	}

}
