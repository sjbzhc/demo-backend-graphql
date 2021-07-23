package com.demobackend.demo.context;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {

        var userId = httpServletRequest.getHeader("user_id");

        var context = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();

        return new CustomGraphQLContext(userId, context);
    }

    /**
     * Subscription (Chapter 33)
     */
    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalStateException("Unsupported");

    }

    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("Unsupported");
    }
}
