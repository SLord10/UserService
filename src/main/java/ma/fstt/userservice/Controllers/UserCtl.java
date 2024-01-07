package ma.fstt.userservice.Controllers;

import ma.fstt.userservice.Entities.User;
import ma.fstt.userservice.Services.UserService;
import ma.fstt.userservice.utlis.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserCtl {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody User user){
        userService.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "saved");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id){
        userService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getall(){
        Map<String, Object> response = new HashMap<>();
        List<User> users = userService.getall();
        if (users != null){
            response.put("data", users);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        }

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String, Object>> getone(@PathVariable("username") String username ) {
        User user = userService.getone(username);
        Map<String, Object> response = new HashMap<>();
        if (user != null){
            response.put("data", user);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getone(@PathVariable("id") Long id ) throws Exception {
        User user = userService.getone(id);
        Map<String, Object> response = new HashMap<>();

        if (user != null){
            response.put("data", user);
            return ResponseEntity.ok(response);
        }
        else {
            response.put("data", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable("id") Long id ,@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        User user1 = userService.getone(id);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        user1.setPhone(user.getPhone());
        user1.setCity(user.getCity());
        user1.setCin(user.getCin());
        user1.setAge(user.getAge());
        userService.update(user1);
        response.put("message", "updated");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> Register(@RequestBody User user){
        return userService.Register(user);
    }
}