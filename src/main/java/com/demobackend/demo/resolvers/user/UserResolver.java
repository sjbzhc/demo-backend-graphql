package com.demobackend.demo.resolvers.user;

import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
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
