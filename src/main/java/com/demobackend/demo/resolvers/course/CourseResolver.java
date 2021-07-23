package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.dataloader.DataLoaderRegistryFactory;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@AllArgsConstructor
public class CourseResolver implements GraphQLResolver<Course> {

    private final UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<User> getCreator(Course course, DataFetchingEnvironment environment) {
        log.info("Getting creator");
        DataLoader<String, User> dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.USER_DATA_LOADER);
        return dataLoader.load(course.getCreatorId());
    }

    public CompletableFuture<List<User>> getParticipants(Course course) {
        return CompletableFuture.supplyAsync(() -> new ArrayList<>(userRepository.findAllById(course.getParticipantsIds())), executorService);
    }
}
