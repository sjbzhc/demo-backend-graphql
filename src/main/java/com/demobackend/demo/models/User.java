package com.demobackend.demo.models;

import com.demobackend.demo.login.model.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("user")
public class User {
    String id;
    String username;
    String name;
    String email;
    String password;
    AuthProvider provider;
    String providerId;
    List<String> roles;
}
