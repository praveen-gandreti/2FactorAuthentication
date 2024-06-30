package springSecurity._FASpringSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springSecurity._FASpringSecurity.Model.Users;
import springSecurity._FASpringSecurity.Repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=usersRepository.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("No Username Found");
		}
		return new CustomUserDetails(user);
	}

}
