package com.santhosh.it.mega.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Weather {

    private String temperature;
    private String rainfall;
    private String windSpeed;
}
