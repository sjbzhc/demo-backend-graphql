package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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

    public Course addParticipantToCourse(String courseId, String participantId) {
        return courseRepository.addParticipant(courseId, participantId);
    }

}
