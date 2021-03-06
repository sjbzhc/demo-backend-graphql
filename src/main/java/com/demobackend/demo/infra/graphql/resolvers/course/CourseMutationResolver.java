package com.demobackend.demo.infra.graphql.resolvers.course;

import com.demobackend.demo.infra.graphql.context.CustomGraphQLContext;
import com.demobackend.demo.domain.exceptions.DemoException;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.domain.repository.CourseRepository;
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

    public Course createCourse(String name, String creatorId) throws DemoException {
            Course course = Course.builder()
                    .name(name)
                    .creatorId(creatorId)
                    .build();

            return courseRepository.save(course).getOrElseThrow(error -> error);
    }

    public Course addParticipantToCourse(String courseId, DataFetchingEnvironment environment) throws DemoException {
        CustomGraphQLContext context = environment.getContext();
        String userEmail = context.getUserEmail();

        return courseRepository.addParticipant(courseId, userEmail).getOrElseThrow(error -> error);
    }

    public Course removeParticipantFromCourse(String courseId, DataFetchingEnvironment environment) throws DemoException {

        CustomGraphQLContext context = environment.getContext();

        String userEmail = context.getUserEmail();

        log.info("Ex-matriculated {} from course with id {}", userEmail, courseId);

        return courseRepository.removeParticipant(courseId, userEmail).getOrElseThrow(error -> error);

    }

}
