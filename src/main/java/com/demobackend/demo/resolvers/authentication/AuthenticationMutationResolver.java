package com.demobackend.demo.resolvers.authentication;

import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationMutationResolver implements GraphQLMutationResolver {
    private final UserRepository userRepository;

//    public User login(String token) {
//
//    }
}
