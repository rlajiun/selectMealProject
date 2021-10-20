package selectMeal.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.BooDaoImpl;
import selectMeal.dao.interFace.BooDao;
import selectMeal.dto.Booking;

public class BooService {
	private BooDao booDao = BooDaoImpl.getInstance();
	private List<Booking> boos = new ArrayList<Booking>();

	public BooService() {
	}

	// 식사 예약
	public boolean makeBoo(int res_id, String id, LocalDateTime dateTime, int num) {
		Booking boo = new Booking(res_id, id, dateTime, num);
		try {
			booDao.insertBooking(boo);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// 예약 확인 - 사용자가 예약한 리스트 출력
	public void checkBoo(String id) {
		boos = booDao.selectBoo(id);
		for (Booking boo : boos) {
			System.out.println(boo);
		}
		if (boos.isEmpty()) {
			System.out.println("예약된 내역이 없습니다.");
		}
	}

}
