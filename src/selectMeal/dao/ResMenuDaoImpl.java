package selectMeal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.interFace.ResMenuDao;
import selectMeal.dto.ResMenu;

public class ResMenuDaoImpl implements ResMenuDao {
	// ResMenuDaoImpl 객체 싱글톤 반환
	private static ResMenuDaoImpl singleton;

	private ResMenuDaoImpl() {
	}

	public static ResMenuDao getInstance() {
		if (singleton == null) {
			singleton = new ResMenuDaoImpl();
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
	public boolean insertResMenu(ResMenu resMenu) throws SQLException {
		boolean result = false;
		if (connect()) {
			String sql = "insert into resmenu values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resMenu.getMenu_name());
			pstmt.setInt(2, resMenu.getRes_id());
			pstmt.setInt(3, resMenu.getPrice());
			int res = pstmt.executeUpdate();
			if (res > 0) {
				result = true;
			}
		}
		close();

		return result;
	}

	@Override
	public List<ResMenu> selectResMenu(int res_id) throws SQLException {
		List<ResMenu> resMenus = new ArrayList<ResMenu>();
		if (connect()) {
			String sql = "select menu_name, price from resmenu where res_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, res_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ResMenu resMenu = new ResMenu();
				resMenu.setMenu_name(rs.getString("menu_name"));
				resMenu.setPrice(rs.getInt("price"));
				resMenus.add(resMenu);
			}
		}
		close();
		
		return resMenus;
	}

}
