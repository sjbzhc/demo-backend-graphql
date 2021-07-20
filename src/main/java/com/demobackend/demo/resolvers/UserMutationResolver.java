package com.demobackend.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserRepository userRepository;

    // The class UserInput should not be used, it is only for GraphQL
    public User createUser(User userInput) {
        User user = User.builder()
                .name(userInput.getName())
                .email(userInput.getEmail())
                .username(userInput.getUsername())
                .build();
        return userRepository.save(user);
    }

}
