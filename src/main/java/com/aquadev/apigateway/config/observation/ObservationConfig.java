package com.aquadev.apigateway.config.observation;

import io.micrometer.observation.ObservationPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.observation.ServerRequestObservationContext;

@Configuration
class ObservationConfig {

    @Bean
    public ObservationPredicate noActuatorTracing() {
        return (_, context) -> {
            if (context instanceof ServerRequestObservationContext serverContext) {
                var request = serverContext.getCarrier();

                if (request instanceof ServerHttpRequest reactiveRequest) {
                    String path = reactiveRequest.getURI().getPath();
                    return path == null || !path.startsWith("/actuator");
                }

                return true;
            }
            return true;
        };
    }
}
