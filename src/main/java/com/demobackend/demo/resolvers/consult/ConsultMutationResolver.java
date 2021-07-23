package com.demobackend.demo.resolvers.consult;

import com.demobackend.demo.models.Consult;
import com.demobackend.demo.repository.ConsultRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultMutationResolver implements GraphQLMutationResolver {

    private final ConsultRepository consultRepository;

    public Consult createConsult(String userId, String content) {
        Consult consult = Consult.builder()
                .content(content)
                .userId(userId)
                .build();

        return consultRepository.save(consult);
    }

}
