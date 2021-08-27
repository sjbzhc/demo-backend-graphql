package com.demobackend.demo.repository;


import com.demobackend.demo.exceptions.DemoException;
import com.demobackend.demo.models.Course;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.List;

public interface CourseRepository {
    Either<DemoException, Course> save(Course course);
    Either<DemoException, List<Course>> findAll();
    Either<DemoException, Course> findById(String courseId);
    Either<DemoException, Course> addParticipant(String courseId, String participantEmail);
    Either<DemoException, List<Course>> findAllByCreatorEmail(String email);

    Either<DemoException, Course> removeParticipant(String courseId, String userEmail);

    Either<DemoException, List<Course>> findAllByParticipantEmail(String userEmail);
}
