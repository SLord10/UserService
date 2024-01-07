package ma.fstt.userservice.Services;

import ma.fstt.userservice.Entities.User;
import ma.fstt.userservice.Repositories.UserRepo;
import ma.fstt.userservice.utlis.JsonConverter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public User getone(String username){
        return UserRepo.findByUsername(username);
    }

    public List<User> getall(){
        return UserRepo.findAll();
    }

    public void update(User User){
        UserRepo.save(User);
    }

    public ResponseEntity<Map<String, String>> Register(User user) {
        User existingUser = UserRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (existingUser == null) {
            UserRepo.save(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "saved");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "the user already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<Map<String, Object>> login(String username, String password) throws Exception {
        User user = UserRepo.findByUsernameAndPassword(username, password);

        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", user);
            return ResponseEntity.ok(response);
        } else {
            if (UserRepo.findByUsername(username) != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "the password is incorrect");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "the user does not exist");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
    }

}