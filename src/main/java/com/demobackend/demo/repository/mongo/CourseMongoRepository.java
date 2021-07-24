package com.demobackend.demo.repository.mongo;

import com.demobackend.demo.models.Course;
import com.demobackend.demo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseMongoRepository implements CourseRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Course> findAll() {
        Query query = new Query();
        return mongoOperations.find(query, Course.class);
    }

    @Override
    public Course addParticipant(String courseId, String participantId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));

        Course currentCourse = mongoOperations.findOne(query, Course.class);
        List<String> participantIds = new ArrayList<>();
        if (currentCourse.getParticipantsIds() != null) {
            participantIds = new ArrayList<>(currentCourse.getParticipantsIds());
        }
        participantIds.add(participantId);

        Update update = new Update();
        update.set("participantsIds", participantIds);
        return mongoOperations.findAndModify(query, update, Course.class);
    }

    @Override
    public Course save(Course course) {
        return mongoOperations.save(course);
    }
}
