package redis.example.redisexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.example.redisexample.model.User;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserResources {
    private UserRepository userRepository;

    public UserResources(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User addUser(@PathVariable String id, @PathVariable String name){
        userRepository.save(new User(id, name,50000L));
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User updateUser(@PathVariable String id, @PathVariable String name){
        userRepository.update(new User(id, name,60000L));
        return userRepository.findById(id);
    }

    @GetMapping("/delete/{id}")
    public Map<String, User> deleteUser(@PathVariable String id){
        userRepository.delete(id);
        return allUser();
    }
    @GetMapping("/all")
    public Map<String,User> allUser(){
        return userRepository.findAll();
    }




}
