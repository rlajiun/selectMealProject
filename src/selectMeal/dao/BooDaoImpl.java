package selectMeal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.interFace.BooDao;
import selectMeal.dto.Booking;

public class BooDaoImpl implements BooDao {
	// BooDaoImpl 객체 싱글톤 반환
	private static BooDaoImpl singleton;

	private BooDaoImpl() {
		}

	public static BooDao getInstance() {
		if (singleton == null) {
			singleton = new BooDaoImpl();
		}
		return singleton;
	}

	// DB connection 및 data 처리 객체 선언
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

	// DB 관련 객체들 닫기
	private void close() {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Booking> selectBoo(String mem_id) {
		List<Booking> boos = new ArrayList<>();
		if (connect()) {
			String sql = "select res_id, to_char(datetime, 'yyyy/mm/dd hh24:mi:ss') as datetime, count from booking where mem_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Booking boo = new Booking();
					boo.setRes(rs.getInt("res_id")); //식당 아이디값만 들어가있음
					boo.setDateTime(rs.getString("datetime"));
					boo.setNumber(rs.getInt("count"));
					boos.add(boo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();

		return boos;
	}

	@Override
	public boolean insertBooking(Booking boo) throws SQLException {
		boolean result = false;
		if (connect()) {
			String sql = "insert into booking values(BOO_SEQ.nextval,?,?,to_date(?, 'yyyy/mm/dd hh24:mi:ss'),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boo.getMem());
			pstmt.setInt(2, boo.getResId());
			pstmt.setString(3, boo.getDateTime());
			pstmt.setInt(4, boo.getNumber());
			System.out.println(boo.getDateTime());
			int res = pstmt.executeUpdate();
			if (res > 0) {
				result = true;
			}
		}
		close();

		return result;
	}

}
