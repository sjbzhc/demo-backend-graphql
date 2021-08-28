package com.demobackend.demo.infra.graphql.resolvers.user;

import com.demobackend.demo.models.User;
import com.demobackend.demo.models.UserInput;
import com.demobackend.demo.domain.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
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
