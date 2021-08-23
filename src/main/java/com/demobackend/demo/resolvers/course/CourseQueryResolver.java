package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.context.CustomGraphQLContext;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
import com.demobackend.demo.repository.CourseRepository;
import com.demobackend.demo.repository.UserRepository;
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

    public List<Course> allCourses(DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAll();
    }

    public Course course(String courseId, DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        Optional<User> user = userRepository.findByEmail(userEmail);

        log.info("Requested course by {}", userEmail);

        Course course = courseRepository.findById(courseId);

        return Course.builder()
                .id(course.getId())
                .name(course.getName())
                .creatorId(course.getCreatorId())
                .description(course.getDescription())
                .isEnrolled(course.getParticipantsIds().contains(user.get().getId()))
                .participantsIds(course.getParticipantsIds())
                .build();
    }

    public List<Course> myOfferedCourses(DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all courses by {}", userEmail);

        return courseRepository.findAllByCreatorEmail(userEmail);
    }

    @PreAuthorize("hasAuthority('student')")
    public List<Course> myCourses(DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Requested all enrolled courses by {}", userEmail);

        return courseRepository.findAllByParticipantEmail(userEmail);
    }
}
