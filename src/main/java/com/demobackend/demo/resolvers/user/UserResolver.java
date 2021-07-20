package com.demobackend.demo.resolvers.user;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLQueryResolver {

    private final UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
