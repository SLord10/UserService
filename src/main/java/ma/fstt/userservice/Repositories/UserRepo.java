package ma.fstt.userservice.Repositories;

import ma.fstt.userservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);

}
