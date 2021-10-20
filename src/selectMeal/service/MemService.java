package selectMeal.service;

import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.MemDaoImpl;
import selectMeal.dao.interFace.MemDao;
import selectMeal.dto.Member;

public class MemService {
	private MemDao memDao = MemDaoImpl.getInstance();
	private static Member mem;

	public MemService() {
	}

	// 로그아웃
	public void logout(String mem_id) {
		mem.setLogin();
	}

	// 로그인
	public String login(String mem_id, String mem_pw) {
		mem = memDao.selectMem(mem_id);
		// 맞는 비밀번호인지 확인
		try {
			if ((mem.getPw()).equals(mem_pw)) {
				mem.setLogin(); // 로그인 처리
				return mem.getName(); // 이름 획득
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	// 회원가입
	public void register(String name, String id, String pw) {
		mem = new Member(name, id, pw);
		memDao.insertMem(mem);
	}

	// 회원 리스트 출력
	public void getMemList() {
		List<Member> members = new ArrayList<Member>();
		members = memDao.selectAllMem();
		for (Member mem : members) {
			System.out.println(mem);
		}

	}

	// 이미 존재하는 회원인지 확인용
	public boolean checkUser(String id) {
		mem = memDao.selectMem(id);
		if (mem.getId() == null) {
			return false; // 존재하지않는 유저
		} else {
			System.out.println(mem);
			return true; // 존재하는 유저
		}
	}

}
