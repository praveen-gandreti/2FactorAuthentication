package springSecurity._FASpringSecurity.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserOtp {
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private int otp;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public UserOtp(String username, int otp) {
		super();
		this.username = username;
		this.otp = otp;
	}
	public UserOtp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
