package com.santhosh.it.mega.handlers;

import com.santhosh.it.mega.models.Weather;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class WeatherHandler {

    public Mono<ServerResponse> getWeather(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(buildWeather(request)));
    }

    private Weather buildWeather(ServerRequest serverRequest){
        if(serverRequest.pathVariable("city").equalsIgnoreCase("Singapore")){
            return Weather.builder().temperature("34.0c").rainfall("33.6mm").windSpeed("22.6km/h").build();
        }
        return Weather.builder().temperature("20c").rainfall("20mm").windSpeed("20km/h").build();

    }
}
