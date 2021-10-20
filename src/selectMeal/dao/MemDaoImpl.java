package selectMeal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.interFace.MemDao;
import selectMeal.dto.Member;

public class MemDaoImpl implements MemDao {

	// MemDaoImpl 객체 싱글톤 반환
	private static MemDaoImpl singleton;

	private MemDaoImpl() {
	}

	public static MemDao getInstance() {
		if (singleton == null) {
			singleton = new MemDaoImpl();
		}
		return singleton;
	}

	// db connection 및 data 처리 객체 선언
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// connection 생성하고 접속 성공하면 return true반환
	private boolean connect() {
		boolean result = false;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "zun";
			String password = "jiun";
			conn = DriverManager.getConnection(url, user, password);
			result = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	// db 관련 객체들 닫기
	private void close() {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Member> selectAllMem() {
		List<Member> members = new ArrayList<>();
		if(connect()) {
			String sql = "select * from member";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Member mem = new Member();
					mem.setName(rs.getString("mem_name"));
					mem.setId(rs.getString("mem_id"));
					mem.setPw(rs.getString("mem_pw"));
					members.add(mem);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		
		return members;
	}
	

	@Override
	public Member selectMem(String mem_id) {
		Member mem = new Member();
		if (connect()) {
			String sql = "select * from member where mem_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					mem.setName(rs.getString("mem_name"));
					mem.setId(rs.getString("mem_id"));
					mem.setPw(rs.getString("mem_pw"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		
		return mem;
	}

	@Override
	public boolean insertMem(Member mem) {
		boolean result = false;
		if(connect()) {
			String sql = "insert into member(mem_id, mem_name, mem_pw) values(?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem.getId());
				pstmt.setString(2, mem.getName());
				pstmt.setString(3, mem.getPw());
				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		
		return result;
	}

	@Override
	public boolean updateMem(Member mem) {
		boolean result = false;
		if(connect()) {
			String sql = "update member "
					+ "set mem_name=?, mem_pw=?"
					+ "where mem_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem.getName());
				pstmt.setString(2, mem.getPw());
				pstmt.setString(3, mem.getId());
				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		
		return result;
	}

	@Override
	public boolean deleteMem(String mem_id) {
		boolean result = false;
		if (connect()) {
			String sql = "delete member where mem_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				int res = pstmt.executeUpdate();
				if (res > 0) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		
		return result;
	}

}
