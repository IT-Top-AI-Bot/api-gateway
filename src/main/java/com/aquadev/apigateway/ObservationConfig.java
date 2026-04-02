package com.aquadev.apigateway;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.observation.ServerRequestObservationContext;

@Configuration
class ObservationConfig {

    @Bean
    ObservationRegistryCustomizer<ObservationRegistry> skipActuatorObservations() {
        return registry -> registry.observationConfig()
                .observationPredicate((name, context) -> {
                    if (context instanceof ServerRequestObservationContext serverContext) {
                        return !serverContext.getCarrier().getRequest().getPath().value().startsWith("/actuator");
                    }
                    return true;
                });
    }
}
