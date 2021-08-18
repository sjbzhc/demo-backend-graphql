package com.demobackend.demo.repository.memory;

import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("it")
public class CourseMemoryRepository implements CourseRepository {
    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public Course findById(String courseId) {
        return Course.builder().build();
    }

    @Override
    public Course addParticipant(String courseId, String participantId) {
        return null;
    }

    @Override
    public List<Course> findAllByCreatorEmail(String email) {
        return List.of();
    }

    @Override
    public Course removeParticipant(String courseId, String userEmail) {
        return null;
    }

    @Override
    public List<Course> findAllByParticipantEmail(String userEmail) {
        return List.of();
    }
}
