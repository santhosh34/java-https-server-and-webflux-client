package com.santhosh.it.mega.routers;

import com.santhosh.it.mega.handlers.WeatherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class WeatherRouter {

    @Bean
    public RouterFunction<ServerResponse> route(WeatherHandler weatherHandler) {

        return RouterFunctions
                .route(GET("/weather/{city}").and(accept(MediaType.APPLICATION_JSON)), weatherHandler::getWeather);
    }
}
