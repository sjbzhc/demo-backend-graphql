package com.demobackend.demo.infra.graphql.resolvers.consult;

import com.demobackend.demo.models.Consult;
import com.demobackend.demo.models.User;
import com.demobackend.demo.domain.repository.UserRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
public class ConsultResolver implements GraphQLResolver<Consult> {
    private UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<User> getUser(Consult consult) {
        return CompletableFuture.supplyAsync(() -> userRepository.findById(consult.getUserId()), executorService);
    }
}
