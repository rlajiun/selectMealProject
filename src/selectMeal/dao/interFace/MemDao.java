package selectMeal.dao.interFace;

import java.util.List;

import selectMeal.dto.Member;

public interface MemDao {
	List<Member> selectAllMem();
	
	Member selectMem(String mem_id);

	boolean insertMem(Member mem);

	boolean updateMem(Member mem);

	boolean deleteMem(String mem_id);
}
