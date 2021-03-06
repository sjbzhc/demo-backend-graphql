package com.demobackend.demo.domain.repository;

import com.demobackend.demo.models.User;

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

    List<String> getRoles(String email);
}
