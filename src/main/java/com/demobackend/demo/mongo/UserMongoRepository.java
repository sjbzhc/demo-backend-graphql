package com.demobackend.demo.mongo;

import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class UserMongoRepository implements UserRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public User save(User user) {
        return mongoOperations.save(user);
    }

    @Override
    public List<User> findAllById(List<String> userIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(userIds));
        return mongoOperations.find(query, User.class);
    }

    @Override
    public User findById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        return mongoOperations.findOne(query, User.class);
    }

    @Override
    public List<User> findAll() {
        Query query = new Query();
        return mongoOperations.find(query, User.class);
    }

    @Override
    public Map<String, User> getCreatorFor(Set<String> userIds) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(userIds));

        List<User> users = mongoOperations.find(query, User.class);

        Map<String, User> map = new HashMap<>();
        users.forEach(user -> map.put(user.getId(), user));

        return map;
    }
}
