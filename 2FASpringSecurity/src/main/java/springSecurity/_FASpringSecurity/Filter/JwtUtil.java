package springSecurity._FASpringSecurity.Filter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import springSecurity._FASpringSecurity.Config.CustomUserDetailsService;

@Service
public class JwtUtil {
	
	@Autowired
	Environment env;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	public String generateToken(String Username)
	{
				String token = Jwts.builder().setSubject(Username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+120000)).signWith(SignatureAlgorithm.HS512,"Chy3EY1SYyxD5rVGYIPl17sotEdZgT0k2r+45FgFizkBDS7f0zH3ITJ1BW5HtbS9qXmb96tRzlsfPIS3EPK4tQ==\r\n").compact();
				return token;
	}
	
	public Authentication validateToken(String Token)
	{
		if(Token!=null)
		{
			try
			{
				Claims claims=Jwts.parser().setSigningKey("Chy3EY1SYyxD5rVGYIPl17sotEdZgT0k2r+45FgFizkBDS7f0zH3ITJ1BW5HtbS9qXmb96tRzlsfPIS3EPK4tQ==\r\n").parseClaimsJws(Token).getBody();
				UserDetails user=customUserDetailsService.loadUserByUsername(claims.getSubject());
				if(user!=null)
				{
					if(!claims.getExpiration().before(new Date()))
					{
						Authentication auth=new UsernamePasswordAuthenticationToken(claims.getSubject(),null,user.getAuthorities());
						return auth;
					}
				}
			}
			catch(Exception e)
			{
				return null;
			}
			
		}
		return null;
	}

}
