package selectMeal.dao.interFace;

import java.sql.SQLException;
import java.util.List;

import selectMeal.dto.ResMenu;

public interface ResMenuDao {

	public List<ResMenu> selectResMenu(int res_id) throws SQLException;

	public boolean insertResMenu(ResMenu resMenu) throws SQLException;
}
