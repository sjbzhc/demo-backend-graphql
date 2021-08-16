package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.context.CustomGraphQLContext;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import com.demobackend.demo.security.CurrentUser;
import com.demobackend.demo.security.UserPrincipal;
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

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAll();
    }

    public Course course(String courseId, DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        System.out.println("courseRepository.findById(courseId)" + courseRepository.findById(courseId));

        return courseRepository.findById(courseId);
    }

    public List<Course> myOfferedCourses(DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAllByCreatorEmail(userEmail);
    }
}
