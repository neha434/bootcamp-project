package redis.example.redisexample;

import redis.example.redisexample.model.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    void save(User user);
    Map<String,User> findAll();
    User findById(String id);
    void update(User user);
    void delete(String id);
}
