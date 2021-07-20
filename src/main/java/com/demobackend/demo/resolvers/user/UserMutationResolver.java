package com.demobackend.demo.resolvers.user;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demobackend.demo.models.User;
import com.demobackend.demo.models.UserInput;
import com.demobackend.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


@Component
@AllArgsConstructor
@Validated
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserRepository userRepository;

    // The class UserInput should not be used, it is only for GraphQL
    public User createUser(@Valid UserInput userInput) {
        User user = User.builder()
                .name(userInput.getName())
                .email(userInput.getEmail())
                .username(userInput.getUsername())
                .build();
        return userRepository.save(user);
    }

}
