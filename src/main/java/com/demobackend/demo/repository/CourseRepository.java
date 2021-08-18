package com.demobackend.demo.repository;


import com.demobackend.demo.models.Course;

import java.util.List;

public interface CourseRepository {
    Course save(Course course);
    List<Course> findAll();
    Course findById(String courseId);
    Course addParticipant(String courseId, String participantEmail);
    List<Course> findAllByCreatorEmail(String email);

    Course removeParticipant(String courseId, String userEmail);

    List<Course> findAllByParticipantEmail(String userEmail);
}
