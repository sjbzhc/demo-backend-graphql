package com.demobackend.demo.resolvers.course;

import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseQueryResolver implements GraphQLQueryResolver {
    private final CourseRepository courseRepository;

    public List<Course> allCourses() {
        return courseRepository.findAll();
    }
}
