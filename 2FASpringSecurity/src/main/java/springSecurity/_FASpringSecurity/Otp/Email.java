package springSecurity._FASpringSecurity.Otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public String mail(int otp,String receiverMail)
	{
		try 
		{
			SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
			simpleMailMessage.setTo(receiverMail);
			simpleMailMessage.setSubject("2 FACTOR AUTHENTICATION");
			simpleMailMessage.setText("OTP : "+otp);
			javaMailSender.send(simpleMailMessage);
			//simpleMailMessage.setText("Please Don't hsare your Password With Other");
			
		}
		catch(Exception e)
		{
			return "Please check your Email Once";
		}
		return "Success";
	}

}
