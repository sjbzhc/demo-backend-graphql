package com.demobackend.demo.resolvers.course;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
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
