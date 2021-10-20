package selectMeal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import selectMeal.dao.interFace.MenuDao;
import selectMeal.dto.Menu;

public class MenuDaoImpl implements MenuDao {

	// MenuDaoImpl 객체 싱글톤 반환
	private static MenuDaoImpl singleton;

	private MenuDaoImpl() {
	}

	public static MenuDao getInstance() {
		if (singleton == null) {
			singleton = new MenuDaoImpl();
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

	// menu 불러오기
	@Override
	public List<Menu> selectAllMenu() {
		List<Menu> menus = new ArrayList<>();
		if (connect()) {
			String sql = "select * from menu";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenu_name(rs.getString("menu_name"));
					menu.setCategory(rs.getString("category"));
					menus.add(menu);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();

		return menus;
	}

	@Override
	public boolean insertMenu(Menu menu) {
		boolean result = false;
		if (connect()) {
			String sql = "insert into menu values(?,?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, menu.getMenu_name());
				pstmt.setString(2, menu.getCategory());
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
