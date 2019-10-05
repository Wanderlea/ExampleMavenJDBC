package model;

public class Telephone {
	
	private Long id;
	private String telephonenumber;
	private String type;
	private Long userpeople;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelephonenumber() {
		return telephonenumber;
	}
	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserpeople() {
		return userpeople;
	}
	public void setUserpeople(Long userpeople) {
		this.userpeople = userpeople;
	}
}
