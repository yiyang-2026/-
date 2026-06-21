package bean;

public class Login {

	private String loginid;
	private String password;
	private String loginAuthority;
	private String frozenflag;
	
	public String getLoginid() {
		return loginid;
	}
	
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLoginAuthority() {
		return loginAuthority;
	}
	
	public void setLoginAuthority(String loginAuthority) {
		this.loginAuthority = loginAuthority;
	}
	
	public String getFrozenflag() {
		return frozenflag;
	}
	
	public void setFrozenflag(String frozenflag) {
		this.frozenflag = frozenflag;
	}
	
}