package com.demobackend.demo.repository.mongo;

import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
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
import java.util.stream.Collectors;

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
    public Course findById(String courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));
        return mongoOperations.findOne(query, Course.class);
    }

    @Override
    public Course addParticipant(String courseId, String userEmail) {
        User user = getUserByEmail(userEmail);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));

        Course currentCourse = mongoOperations.findOne(query, Course.class);
        List<String> participantIds = new ArrayList<>();
        if (currentCourse.getParticipantsIds() != null) {
            participantIds = new ArrayList<>(currentCourse.getParticipantsIds());
        }
        participantIds.add(user.getId());

        Update update = new Update();
        update.set("participantsIds", participantIds);
        return mongoOperations.findAndModify(query, update, Course.class);
    }

    @Override
    public List<Course> findAllByCreatorEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        User user = mongoOperations.findOne(query, User.class);

        query = new Query();
        query.addCriteria(Criteria.where("creatorId").is(user.getId()));
        return mongoOperations.find(query, Course.class);
    }

    @Override
    public Course removeParticipant(String courseId, String userEmail) {
        User user = getUserByEmail(userEmail);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));
        Course course = mongoOperations.findOne(query, Course.class);

        List<String> participantIds = new ArrayList<>();
        if (course.getParticipantsIds() != null) {
            participantIds = new ArrayList<>(course.getParticipantsIds());
        }

        List<String> participants = participantIds.stream().filter(pId -> !pId.equals(user.getId())).collect(Collectors.toList());
        Update update = new Update();
        update.set("participantsIds", participants);
        return mongoOperations.findAndModify(query, update, Course.class);
    }

    @Override
    public List<Course> findAllByParticipantEmail(String userEmail) {
        User user = getUserByEmail(userEmail);
        // TODO: create a proper mongo query
        return findAll().stream().filter(course -> course.getParticipantsIds().contains(user.getId())).collect(Collectors.toList());
    }

    @Override
    public Course save(Course course) {
        return mongoOperations.save(course);
    }

    private User getUserByEmail(String userEmail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(userEmail));
        return mongoOperations.findOne(query, User.class);
    }
}
