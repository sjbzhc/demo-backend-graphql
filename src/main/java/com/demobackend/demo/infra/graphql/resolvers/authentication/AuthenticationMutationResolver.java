package com.demobackend.demo.infra.graphql.resolvers.authentication;

import com.demobackend.demo.domain.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationMutationResolver implements GraphQLMutationResolver {
    private final UserRepository userRepository;
}
