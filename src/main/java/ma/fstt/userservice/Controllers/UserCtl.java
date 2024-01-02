package ma.fstt.userservice.Controllers;

import ma.fstt.userservice.Entities.User;
import ma.fstt.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserCtl {

    @Autowired
    UserService userService;

    @PostMapping("")
    public String add(@RequestBody User user){
        userService.save(user);
        return "saved";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "deleted";
    }

    @GetMapping("")
    public List<User> getall(){
        return userService.getall();
    }

    @GetMapping("/{id}")
    public User getone(@PathVariable("id") Long id ){
        return userService.getone(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id ,@RequestBody User user) {
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
        return "updated";
    }

    @PostMapping("/login")
    public String login( @RequestBody User user){
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    public String Register(@RequestBody User user){
        return userService.Register(user);
    }




}
