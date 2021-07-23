package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.context.CustomGraphQLContext;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseQueryResolver implements GraphQLQueryResolver {
    private final CourseRepository courseRepository;

    public List<Course> allCourses(DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userId = context.getUserId();

        log.info("Requested all courses by {}", userId);

        return courseRepository.findAll();
    }
}
