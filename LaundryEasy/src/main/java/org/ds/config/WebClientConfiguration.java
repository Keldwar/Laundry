package org.ds.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Конфигурация {@link WebClient} для отправки запросов на сервер ландромата.
 */
@Configuration
public class WebClientConfiguration {
    /**
     * Адрес сервера ландромата, на который будут отправлять запросы
     */
    @Value("${api.base.url}")
    private String baseUrl;
    @Value("${api.timeout}")
    private int timeout;

    @Bean
    public WebClient webClientWithTimeout() {
        final HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder().baseUrl(baseUrl).clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
