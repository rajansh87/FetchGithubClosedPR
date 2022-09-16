package com.example.demo.config

import io.netty.handler.logging.LogLevel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import reactor.netty.transport.logging.AdvancedByteBufFormat
import java.time.Duration

@Configuration
class WebClientConfiguration {

    @Bean
    fun jettyHttpClient(webClientBuilder: WebClient.Builder): WebClient? {

        val provider: ConnectionProvider = ConnectionProvider.builder("webclientConfig")
            .maxConnections(500)
            .maxIdleTime(Duration.ofSeconds(20))
            .maxLifeTime(Duration.ofSeconds(60))
            .pendingAcquireTimeout(Duration.ofSeconds(60))
            .evictInBackground(Duration.ofSeconds(120)).build()


        val httpClient: HttpClient = HttpClient.create(provider)
            .wiretap(this.javaClass.canonicalName, LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL)
        val conn: ClientHttpConnector = ReactorClientHttpConnector(httpClient)

        return webClientBuilder
            .clientConnector(conn)
            .build()
    }
}
