package com.santhosh.it.mega.services;

import com.santhosh.it.mega.models.Weather;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Component
public class WeatherClient {
    private final WebClient client;

    public WeatherClient(WebClient.Builder builder) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException {
        Path truststorePath = Path.of(ClassLoader.getSystemResource("clientkeystore.jks").toURI());
        InputStream truststoreInputStream = Files.newInputStream(truststorePath, StandardOpenOption.READ);

        KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
        truststore.load(truststoreInputStream, "password".toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(truststore);

        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(trustManagerFactory)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(sslSpec -> sslSpec.sslContext(sslContext));

        this.client= WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://127.0.0.1:8443")
                .build();
    }

    public Mono<Weather> getWeatherIn(String city) {
        return this.client.get().uri("/weather/"+city).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Weather.class);
    }

}
