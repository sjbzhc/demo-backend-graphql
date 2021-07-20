package com.demobackend.demo.repository;

import com.demobackend.demo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> { }
