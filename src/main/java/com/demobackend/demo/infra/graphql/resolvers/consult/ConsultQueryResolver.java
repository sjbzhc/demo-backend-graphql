package com.demobackend.demo.infra.graphql.resolvers.consult;

import com.demobackend.demo.models.Consult;
import com.demobackend.demo.domain.repository.ConsultRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultQueryResolver implements GraphQLQueryResolver {

    private final ConsultRepository consultRepository;

    public List<Consult> consultByUserId(String id) {
        return consultRepository.findAllByUserId(id);
    }

    public List<Consult> allConsults() {
        return consultRepository.findAll();
    }

}
