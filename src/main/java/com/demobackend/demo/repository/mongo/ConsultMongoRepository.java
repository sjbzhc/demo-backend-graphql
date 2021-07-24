package com.demobackend.demo.repository.mongo;

import com.demobackend.demo.models.Consult;
import com.demobackend.demo.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultMongoRepository implements ConsultRepository {
    @Autowired
    MongoOperations mongoOperations;
    @Override
    public List<Consult> findAllByUserId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        return mongoOperations.find(query, Consult.class);
    }

    @Override
    public List<Consult> findAll() {
        Query query = new Query();
        return mongoOperations.find(query, Consult.class);
    }

    @Override
    public Consult save(Consult consult) {
        return mongoOperations.save(consult);
    }
}
