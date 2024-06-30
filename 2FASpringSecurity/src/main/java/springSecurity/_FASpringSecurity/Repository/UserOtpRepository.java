package springSecurity._FASpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springSecurity._FASpringSecurity.Model.UserOtp;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp,Integer>{

	UserOtp findByUsername(String username);

}
