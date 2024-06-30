package springSecurity._FASpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springSecurity._FASpringSecurity.Model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

	Users findByUsername(String username);

}
