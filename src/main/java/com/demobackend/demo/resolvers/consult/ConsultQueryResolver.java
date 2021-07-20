package com.demobackend.demo.resolvers.consult;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demobackend.demo.models.Consult;
import com.demobackend.demo.repository.ConsultRepository;
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
