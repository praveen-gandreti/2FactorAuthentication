package springSecurity._FASpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springSecurity._FASpringSecurity.Model.LoginRequest;
import springSecurity._FASpringSecurity.Model.Users;
import springSecurity._FASpringSecurity.Otp.Email;
import springSecurity._FASpringSecurity.Repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserOtpService UserOtpService;
	
	@Autowired
	Email email;
	
	public Users save(Users user)
	{
		return usersRepository.save(user);
	}
	
	public boolean validate(LoginRequest credentials)
	{
		Users user=usersRepository.findByUsername(credentials.getUsername());
		if(user!=null)
		{
			if(credentials.getPassword().equals(user.getPassword()))
			{
				int OTP=(int)(Math.random()*(9999-1000)+1000);
				String msg=email.mail(OTP, user.getUsername());
				UserOtpService.save(user.getUsername(), OTP);
				if(msg.equalsIgnoreCase("success"))
				{
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		return false;
	}

}
