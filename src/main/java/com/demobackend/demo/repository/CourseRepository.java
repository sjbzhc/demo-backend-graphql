package com.demobackend.demo.repository;


import com.demobackend.demo.models.Course;

import java.util.List;

public interface CourseRepository {
    Course save(Course course);
    List<Course> findAll();
    Course findById(String courseId);
    Course addParticipant(String courseId, String participantId);
    List<Course> findAllByCreatorEmail(String email);
}
