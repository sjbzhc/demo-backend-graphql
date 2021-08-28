package com.demobackend.demo.domain.repository.mongo;

import com.demobackend.demo.domain.exceptions.CourseRepositoryException;
import com.demobackend.demo.domain.exceptions.DemoException;
import com.demobackend.demo.models.Course;
import com.demobackend.demo.models.User;
import com.demobackend.demo.domain.repository.CourseRepository;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CourseMongoRepository implements CourseRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Either<DemoException, List<Course>> findAll() {
        try {
            Query query = new Query();
            return Either.right(mongoOperations.find(query, Course.class));
        } catch (Exception e) {
            return Either.left(new CourseRepositoryException.RepositoryAccessException());
        }
    }

    @Override
    public Either<DemoException, Course> findById(String courseId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(courseId));
            return Either.right(mongoOperations.findOne(query, Course.class));
        } catch (Exception e) {
            return Either.left(new CourseRepositoryException.RepositoryAccessException());
        }

    }

    @Override
    public Either<DemoException, Course> addParticipant(String courseId, String userEmail) {
        User user = getUserByEmail(userEmail);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));

        Update update = new Update();
        update.addToSet("participantsIds", user.getId());

        mongoOperations.updateFirst(query, update, Course.class).getModifiedCount();

        return findById(courseId);
    }

    @Override
    public Either<DemoException, List<Course>> findAllByCreatorEmail(String email) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            User user = mongoOperations.findOne(query, User.class);

            query = new Query();
            query.addCriteria(Criteria.where("creatorId").is(user.getId()));
            return Either.right(mongoOperations.find(query, Course.class));
        } catch (Exception e) {
            return Either.left(new CourseRepositoryException.RepositoryAccessException());
        }

    }

    @Override
    public Either<DemoException, Course> removeParticipant(String courseId, String userEmail) {
        User user = getUserByEmail(userEmail);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(courseId));

        Update update = new Update();
        update.pull("participantsIds", user.getId());

        mongoOperations.updateFirst(query, update, Course.class).getModifiedCount();

        return findById(courseId);
    }

    @Override
    public Either<DemoException, List<Course>> findAllByParticipantEmail(String userEmail) {
        try {
            User user = getUserByEmail(userEmail);
            Query query = new Query();
            query.addCriteria(Criteria.where("participantsIds").is(user.getId()));
            return Either.right(mongoOperations.find(query, Course.class));
        } catch (Exception e) {
            return Either.left(new CourseRepositoryException.RepositoryAccessException());
        }

    }

    @Override
    public Either<DemoException, Course> save(Course course) {
        try {
            return Either.right(mongoOperations.save(course));
        } catch (Exception e) {
            return Either.left(new CourseRepositoryException.RepositoryAccessException());
        }
    }

    private User getUserByEmail(String userEmail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(userEmail));
        return mongoOperations.findOne(query, User.class);
    }
}
