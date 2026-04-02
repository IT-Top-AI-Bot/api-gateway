package com.aquadev.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.observation.DefaultServerRequestObservationConvention;
import org.springframework.http.server.reactive.observation.ServerRequestObservationContext;
import org.springframework.http.server.reactive.observation.ServerRequestObservationConvention;

@Configuration
public class GatewayObservationConfig {

    @Bean
    public ServerRequestObservationConvention serverRequestObservationConvention() {
        return new DefaultServerRequestObservationConvention() {
            @Override
            public String getContextualName(ServerRequestObservationContext context) {
                String method = context.getCarrier().getMethod().name();
                String path = context.getCarrier().getURI().getPath();
                return method + " " + path;
            }
        };
    }
}
