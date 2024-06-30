package springSecurity._FASpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springSecurity._FASpringSecurity.Exception.OtpException;
import springSecurity._FASpringSecurity.Exception.UserNotFoundException;
import springSecurity._FASpringSecurity.Filter.JwtUtil;
import springSecurity._FASpringSecurity.Model.UserOtp;
import springSecurity._FASpringSecurity.Repository.UserOtpRepository;

@Service
public class UserOtpService {
	
	@Autowired
	UserOtpRepository userOtpRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public UserOtp save(String username,int otp)
	{
		UserOtp obj=new UserOtp();
		obj.setUsername(username);
		obj.setOtp(otp);
		return userOtpRepository.save(obj);
	}
	
	public String generate(UserOtp userOtp)
	{
		UserOtp user=userOtpRepository.findByUsername(userOtp.getUsername());
		if(user!=null)
		{
			if(user.getOtp()==userOtp.getOtp())
			{
				String token = jwtUtil.generateToken(user.getUsername());
				return token;
			}
			else
			{
				throw new OtpException("Incorrect OTP");
			}
		}
		throw new UserNotFoundException("USER IS NOT FOUND");
	}
	

}
