package com.demobackend.demo.resolvers.course;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class CourseResolver implements GraphQLResolver<Course> {

    private final UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<User> getCreator(Course course) {
        return CompletableFuture.supplyAsync(() -> userRepository.findById(course.getCreatorId()).get(), executorService);
    }

    public CompletableFuture<List<User>> getParticipants(Course course) {
        return CompletableFuture.supplyAsync(() -> StreamSupport.stream(
                userRepository.findAllById(course.getParticipantsIds()).spliterator(), false)
                .collect(Collectors.toList()), executorService);
    }
}
