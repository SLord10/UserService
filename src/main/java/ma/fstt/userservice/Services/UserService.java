package ma.fstt.userservice.Services;

import ma.fstt.userservice.Entities.User;
import ma.fstt.userservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String Register(User user){
        User user1 = UserRepo.findByUsernameOrEmail(user.getUsername(),user.getEmail());
        if (user1 == null){
            UserRepo.save(user);
            return "saved";
        }
        else
            return "the user already exists";
    }

    public String login(String username, String password){
        User user = UserRepo.findByUsernameAndPassword(username,password);
        if (user != null)
            return user.getUsername();
        else
            if (UserRepo.findByUsername(username) != null)
                return "the password is incorrect";
                else
                    return "the user does not exist";
    }




}
