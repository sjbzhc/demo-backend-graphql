package com.demobackend.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("course")
public class Course {
    ObjectId id;
    String name;
    String creatorId;
    String description;
    List<String> participantsIds;

    public List<String> getParticipantsIds() {
        if (participantsIds != null) {
            return participantsIds;
        }
        return List.of();
    }
}
