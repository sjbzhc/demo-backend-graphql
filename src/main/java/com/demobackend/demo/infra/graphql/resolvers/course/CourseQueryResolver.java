package com.demobackend.demo.infra.graphql.resolvers.course;

import com.demobackend.demo.infra.graphql.context.CustomGraphQLContext;
import com.demobackend.demo.domain.exceptions.DemoException;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
import com.demobackend.demo.domain.repository.CourseRepository;
import com.demobackend.demo.domain.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseQueryResolver implements GraphQLQueryResolver {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> allCourses(DataFetchingEnvironment environment) throws Throwable {

        CustomGraphQLContext context = environment.getContext();
        String userEmail = context.getUserEmail();
        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAll().getOrElseThrow(error -> error);
    }

    public Course course(String courseId, DataFetchingEnvironment environment) throws DemoException {

        CustomGraphQLContext context = environment.getContext();
        String userEmail = context.getUserEmail();

        Optional<User> user = userRepository.findByEmail(userEmail);

        log.info("Requested course by {}", userEmail);

        Course course = courseRepository.findById(courseId).getOrElseThrow(error -> error);

        return Course.builder()
                .id(course.getId())
                .name(course.getName())
                .creatorId(course.getCreatorId())
                .description(course.getDescription())
                .isEnrolled(course.getParticipantsIds().contains(user.get().getId()))
                .participantsIds(course.getParticipantsIds())
                .units(course.getUnits())
                .build();
    }

    public List<Course> myOfferedCourses(DataFetchingEnvironment environment) throws DemoException {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAllByCreatorEmail(userEmail).getOrElseThrow(error -> error);
    }

    @PreAuthorize("hasAuthority('student')")
    public List<Course> myCourses(DataFetchingEnvironment environment) throws DemoException {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all enrolled courses by {}", userEmail);

        return courseRepository.findAllByParticipantEmail(userEmail).getOrElseThrow(error -> error);
    }
}
