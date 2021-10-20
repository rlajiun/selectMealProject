package selectMeal.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import selectMeal.dao.ResDaoImpl;
import selectMeal.dao.interFace.ResDao;

public class Booking {
	private Restaurant res; // 식당
	private String mem; // 예약자
	private LocalDateTime dateTime; // 예약 시간 날짜
	private int number; // 예약 인원
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public Booking() {
	}

	public Booking(int res_id, String mem, LocalDateTime dateTime, int number) {
		ResDao resDao = ResDaoImpl.getInstance();
		this.res = resDao.selectRes(res_id);
		this.mem = mem;
		this.dateTime = dateTime;
		this.number = number;
	}

	public Restaurant getRes() {
		return res;
	}

	public int getResId() {
		return res.getRes_id();
	}

	public void setRes(Restaurant res) {
		this.res = res;
	}

	public void setRes(int res_id) {
		ResDao resDao = ResDaoImpl.getInstance();
		this.res = resDao.selectRes(res_id);
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getDateTime() {
		return dateTime.format(formatter);
	}

	public void setDateTime(String dateTime) {
		LocalDateTime datetime = LocalDateTime.parse(dateTime, formatter);
		this.dateTime = datetime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "예약 장소: " + res.getResName() + ", 예약 시간: " + dateTime.format(formatter) + ", 인원: " + number;
	}
}
