package selectMeal.dao.interFace;

import java.util.List;

import selectMeal.dto.Restaurant;

public interface ResDao {
	public List<Restaurant> selectAllRes();
	
	public Restaurant selectRes(int res_id);

	public boolean insertRes(Restaurant res) throws Exception;
}
