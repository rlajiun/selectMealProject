package selectMeal.dto;

//import java.io.Serializable;

public class Member /* implements Serializable */ {

	/**
	 * 
	 */
//	private static final long serialVersionUID = -3669338943983572932L;
	private String mem_name; //회원이름
	private String mem_id; //회원 아이디
	private String mem_pw; //회원 비밀번호
	transient private boolean login = false; //로그인 유무 확인용
	
	public Member() {
	}
	
	public Member(String mem_name, String mem_id, String mem_pw) {
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}

	public String getName() {
		return mem_name;
	}

	public void setName(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getId() {
		return mem_id;
	}

	public void setId(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getPw() {
		return mem_pw;
	}

	public void setPw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin() { 
		this.login = !login;
	}

	@Override
	public String toString() {
		return "이름: " + mem_name + ", ID: " + mem_id + ", PW: " + mem_pw;
	}

}
