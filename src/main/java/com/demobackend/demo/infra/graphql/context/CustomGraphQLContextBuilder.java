package com.demobackend.demo.infra.graphql.context;

import com.demobackend.demo.infra.graphql.dataloader.DataLoaderRegistryFactory;
import com.demobackend.demo.domain.exceptions.InvalidTokenException;
import com.demobackend.demo.domain.repository.UserRepository;
import com.demobackend.demo.security.GoogleTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private final DataLoaderRegistryFactory dataLoaderRegistryFactory;
    private final GoogleTokenVerifier googleTokenVerifier;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {

        GoogleIdToken.Payload user;
        String jwt = getJwtFromRequest(httpServletRequest);

        if (StringUtils.hasText(jwt) && googleTokenVerifier.isValid(jwt)) {
            user = googleTokenVerifier.verify(jwt);
        } else {
            throw new InvalidTokenException("The token is not valid");
        }

        List<String> roles = userRepository.getRoles(user.getEmail());

        var context = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderRegistryFactory.create())
                .build();

        return new CustomGraphQLContext(user.getEmail(), roles, context);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("Unsupported");

    }

    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("Unsupported");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
