package com.demobackend.demo.domain.repository.memory;

import com.demobackend.demo.domain.exceptions.DemoException;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.domain.repository.CourseRepository;
import io.vavr.control.Either;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("it")
public class CourseMemoryRepository implements CourseRepository {
    @Override
    public Either<DemoException, Course> save(Course course) {
        return null;
    }

    @Override
    public Either<DemoException, List<Course>> findAll() {
        return Either.right(List.of());
    }

    @Override
    public Either<DemoException, Course> findById(String courseId) {
        return Either.right(Course.builder().build());
    }

    @Override
    public Either<DemoException, Course> addParticipant(String courseId, String participantId) {
        return Either.right(Course.builder().build());
    }

    @Override
    public Either<DemoException, List<Course>> findAllByCreatorEmail(String email) {
        return Either.right(List.of());
    }

    @Override
    public Either<DemoException, Course> removeParticipant(String courseId, String userEmail) {
        return Either.right(Course.builder().build());
    }

    @Override
    public Either<DemoException, List<Course>> findAllByParticipantEmail(String userEmail) {
        return Either.right(List.of());
    }
}
