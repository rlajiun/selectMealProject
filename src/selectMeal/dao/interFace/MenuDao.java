package selectMeal.dao.interFace;

import java.util.List;

import selectMeal.dto.Menu;

public interface MenuDao {
	public List<Menu> selectAllMenu();

	boolean insertMenu(Menu menu);
}
