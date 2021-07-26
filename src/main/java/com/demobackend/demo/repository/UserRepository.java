package com.demobackend.demo.repository;

import com.demobackend.demo.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    User save(User user);

    List<User> findAllById(List<String> participantsIds);

    User findById(String userId);

    Optional<User> findOptionalById(String userId);

    List<User> findAll();

    Map<String, User> getCreatorFor(Set<String> userIds);

    Optional<User> findByEmail(String email);
}
