package ma.fstt.userservice.Services;

import ma.fstt.userservice.Entities.User;
import ma.fstt.userservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo UserRepo;

    public void save(User User){
        UserRepo.save(User);
    }

    public void delete(Long id){
        UserRepo.deleteById(id);
    }

    public User getone(Long id){
        return UserRepo.findById(id).get();
    }

    public List<User> getall(){
        return UserRepo.findAll();
    }

    public void update(User User){
        UserRepo.save(User);
    }

    public ResponseEntity<String> Register(User user) {
        User existingUser = UserRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (existingUser == null) {
            UserRepo.save(user);
            return ResponseEntity.ok("{\"message\": \"saved\"}");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"the user already exists\"}");
        }
    }


    public ResponseEntity<String> login(String username, String password) {
        User user = UserRepo.findByUsernameAndPassword(username, password);

        if (user != null) {
            return ResponseEntity.ok("{\"message\": \"" + user.getUsername() + "\"}");
        } else {
            if (UserRepo.findByUsername(username) != null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"the password is incorrect\"}");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"the user does not exist\"}");
            }
        }
    }
}