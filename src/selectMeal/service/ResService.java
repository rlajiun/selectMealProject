package selectMeal.service;

import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.ResDaoImpl;
import selectMeal.dao.interFace.ResDao;
import selectMeal.dto.Restaurant;

public class ResService {
	private ResDao resDao = ResDaoImpl.getInstance();
	private List<Restaurant> restaurants = new ArrayList<>();

	public ResService() {
		getResList(); // 생성하면 식당 목록 불러오기
	}

	// 식당 추가
	public boolean addRes(String resName, String pNumber, String address) {
		try {
			Restaurant res = new Restaurant(resName, pNumber, address);
			resDao.insertRes(res);
			restaurants.add(res);
			getResList();
			return true; // 식당 등록 완료
		} catch (Exception e) {
			return false; // 식당 등록 실패
		}
	}

	// DB에서 식당 정보 불러오기
	public void getResList() {
		restaurants = resDao.selectAllRes();
	}

	// 식당 목록 출력
	public void printResList() {
		for (Restaurant res : restaurants) {
			System.out.println(res);
		}
	}

	// 해당 이름과 일치하는 식당 목록 출력
	public boolean printResList(String resName) {
		List<Restaurant> ress = new ArrayList<Restaurant>();
		for (Restaurant res : restaurants) {
			if ((res.getResName()).equals(resName)) {
				System.out.println(res);
				ress.add(res);
			}
		}
		if (ress.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// 존재하는 식당인지 확인용
	public boolean checkRes(String resName) {
		for (Restaurant res : restaurants) {
			if (!(res.getResName()).equals(resName)) {
				continue;
			} else {
				return true; // 일치하는 거 있음
			}
		}
		return false; // 없음
	}

}
