package com.santhosh.it.mega.controllers;

import com.santhosh.it.mega.models.Weather;
import com.santhosh.it.mega.services.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
public class GamesOrgController {

        @Autowired
        private WeatherClient weatherClient;


        @GetMapping("/{gameCity}")
        public Mono<Weather> getWeatherInGameCity(@PathVariable String gameCity){
             return weatherClient.getWeatherIn(gameCity);
        }

}
