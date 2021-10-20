package selectMeal.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.ResDaoImpl;
import selectMeal.dao.ResMenuDaoImpl;
import selectMeal.dao.interFace.ResDao;
import selectMeal.dao.interFace.ResMenuDao;
import selectMeal.dto.ResMenu;
import selectMeal.dto.Restaurant;

public class ResMenuService {
	private ResDao resDao = ResDaoImpl.getInstance();
	private ResMenuDao resMenuDao = ResMenuDaoImpl.getInstance();
	private List<Restaurant> ress = new ArrayList<Restaurant>();

	public ResMenuService() {
	}

	// 식당 메뉴 등록
	public boolean addResMenu(String foodName, int res_id, int price) {
		try {
			resMenuDao.insertResMenu(new ResMenu(foodName, res_id, price));
			return true; // 메뉴 등록 완료
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 메뉴 등록 실패 - 이미 존재하는 메뉴
		}
	}

	// 식당에 메뉴 리스트 만들어서 넣어주기
	public void getResMenu() {
		ress = resDao.selectAllRes();
		for (Restaurant res : ress) {
			try {
				res.setMenuList(resMenuDao.selectResMenu(res.getRes_id()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 해당 음식을 가지고 있는 식당 리스트 출력
	public List<Restaurant> printResList(String foodName) {
		List<Restaurant> selRes = new ArrayList<Restaurant>();
		List<ResMenu> resMenus = new ArrayList<ResMenu>();

		getResMenu();
		for (Restaurant res : ress) {
			resMenus = res.getMenuList();
			for (ResMenu resMenu : resMenus) {
				if ((resMenu.getMenu_name()).equals(foodName)) {
					selRes.add(res); // 해당 메뉴가 존재하는 식당
				}
			}
		}

		return selRes;
	}

//	// 해당 식당 메뉴 리스트 출력
//	public Restaurant printResMenuList(int res_id) {
//		resMenus = restaurants.get(res - 1).getMenuList();
//
//		int i = 0;
//		for (ResMenu resMenu : resMenus) {
//			System.out.printf("[%d] %s\n", i + 1, resMenu);
//			i++;
//		}
//		return restaurants.get(res - 1);
//	}
//

}
