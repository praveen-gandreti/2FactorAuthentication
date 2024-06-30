package springSecurity._FASpringSecurity.Controller;

import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springSecurity._FASpringSecurity.Model.LoginRequest;
import springSecurity._FASpringSecurity.Model.UserOtp;
import springSecurity._FASpringSecurity.Model.Users;
import springSecurity._FASpringSecurity.Service.UserOtpService;
import springSecurity._FASpringSecurity.Service.UsersService;

@RestController
public class Controller {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UserOtpService userOtpService;
	
	@PostMapping("user/save")
	public ResponseEntity<Users> saveUser(@RequestBody Users user) throws DataFormatException
	{
		try {
			return new ResponseEntity<>(usersService.save(user),HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new DataFormatException("Data Format Is NOt Correct");
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest credentials)
	{
		if(usersService.validate(credentials))
		{
			return new ResponseEntity<>("AUTHENTICATION SUCCESSFULL",HttpStatus.OK);
		}
		return new ResponseEntity<>("AUTHENTICATION FAILED",HttpStatus.UNAUTHORIZED);
		
	}
	
	@PostMapping("generate")
	public ResponseEntity<String> generate(@RequestBody UserOtp userOtp)
	{
		String token=userOtpService.generate(userOtp);
		if(token!=null)
		{
			return new ResponseEntity<>(token,HttpStatus.OK);
		}
		return new ResponseEntity<>("AUTHENTICATION FAILED - OTP INCORRECT",HttpStatus.UNAUTHORIZED);
		
	}
	
	@GetMapping("/demo")
	public String demo()
	{
		return "THIS DEMO";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "ADMIN HERE";
	}
	
	
//	@GetMapping("send/{to}")
//	public String send(@PathVariable("to") String to)
//	{
//		int otp=(int)(Math.random()*(9999-1000))+1000;
//		return email.mail(otp, to);
//	}
//	

}
