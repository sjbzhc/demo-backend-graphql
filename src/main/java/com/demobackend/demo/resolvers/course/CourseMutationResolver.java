package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.context.CustomGraphQLContext;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CourseMutationResolver implements GraphQLMutationResolver {
    private final CourseRepository courseRepository;

    public Course createCourse(String name, String creatorId) {
        Course course = Course.builder()
                .name(name)
                .creatorId(creatorId)
                .build();

        return courseRepository.save(course);
    }

    public Course addParticipantToCourse(String courseId, DataFetchingEnvironment environment) {
        CustomGraphQLContext context = environment.getContext();
        String userEmail = context.getUserEmail();

        return courseRepository.addParticipant(courseId, userEmail);
    }

    public Course removeParticipantFromCourse(String courseId, DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Ex-matriculated {} from course with id {}", userEmail, courseId);

        return courseRepository.removeParticipant(courseId, userEmail);

    }

}
