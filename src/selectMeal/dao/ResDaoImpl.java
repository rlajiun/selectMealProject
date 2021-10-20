package selectMeal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.interFace.ResDao;
import selectMeal.dto.Restaurant;

public class ResDaoImpl implements ResDao {
	// ResDaoImpl 객체 싱글톤 반환
	private static ResDaoImpl singleton;

	private ResDaoImpl() {
	}

	public static ResDao getInstance() {
		if (singleton == null) {
			singleton = new ResDaoImpl();
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
	public List<Restaurant> selectAllRes() {
		List<Restaurant> ress = new ArrayList<>();
		if (connect()) {
			String sql = "select * from restaurant";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Restaurant res = new Restaurant();
					res.setRes_id(rs.getInt("res_id"));
					res.setResName(rs.getString("res_name"));
					res.setpNumber(rs.getString("res_phone"));
					res.setAddress(rs.getString("res_address"));
					ress.add(res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();

		return ress;
	}
	
	@Override
	public Restaurant selectRes(int res_id) {
		Restaurant res = new Restaurant();
		if (connect()) {
			String sql = "select * from restaurant where res_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, res_id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					res.setRes_id(rs.getInt("res_id"));
					res.setResName(rs.getString("res_name"));
					res.setpNumber(rs.getString("res_phone"));
					res.setAddress(rs.getString("res_address"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();

		return res;
	}

	@Override
	public boolean insertRes(Restaurant res) throws SQLException {
		boolean result = false;
		if (connect()) {
			String sql = "insert into restaurant values(RES_SEQ.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, res.getResName());
			pstmt.setString(2, res.getpNumber());
			pstmt.setString(3, res.getAddress());
			int resu = pstmt.executeUpdate();
			if (resu > 0) {
				result = true;
			}
		}
		close();

		return result;
	}

}
