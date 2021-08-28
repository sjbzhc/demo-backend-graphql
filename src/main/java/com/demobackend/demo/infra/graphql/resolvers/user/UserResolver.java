package com.demobackend.demo.infra.graphql.resolvers.user;

import com.demobackend.demo.models.User;
import com.demobackend.demo.domain.repository.UserRepository;
import com.demobackend.demo.security.CurrentUser;
import com.demobackend.demo.security.UserPrincipal;
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

    public User currentUser(@CurrentUser UserPrincipal userPrincipal) {
        return User.builder().build();
    }
}
