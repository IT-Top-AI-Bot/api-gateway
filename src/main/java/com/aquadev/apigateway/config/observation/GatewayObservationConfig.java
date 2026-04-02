package com.aquadev.apigateway.config.observation;

import org.jspecify.annotations.NonNull;
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
            public String getContextualName(@NonNull ServerRequestObservationContext context) {
                var request = context.getCarrier();

                if (request == null) {
                    return "UNKNOWN";
                }

                String method = request.getMethod().name();
                String path = request.getURI().getPath();
                return method + " " + path;
            }
        };
    }
}
