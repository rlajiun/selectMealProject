package selectMeal.dao.interFace;

import java.sql.SQLException;
import java.util.List;

import selectMeal.dto.Booking;

public interface BooDao {
	public List<Booking> selectBoo(String mem_id);

	boolean insertBooking(Booking boo) throws SQLException;
}
