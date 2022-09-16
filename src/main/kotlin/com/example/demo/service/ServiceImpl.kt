package com.example.demo.service

import com.example.demo.config.GitConfig
import com.example.demo.exception.controller.DataNotFoundException
import com.example.demo.model.ResponseModelDTO
import com.example.demo.utils.Utils
import mu.KLogger
import mu.KotlinLogging
import org.springframework.web.reactive.function.client.WebClient
import java.net.URL


@org.springframework.stereotype.Service
class ServiceImpl(private val gitConfig: GitConfig):Service{
    private val logger: KLogger = KotlinLogging.logger {}

    @Override
    override fun getAllClosedPRs(): MutableList<ResponseModelDTO>? {
        val webClient: WebClient = WebClient.create(gitConfig.url)
        val responseListDTO = mutableListOf<ResponseModelDTO>()
        webClient.get()
            .uri("/pulls?state=closed")
            .header("Authorization", "Bearer ${gitConfig.token}")
            .retrieve()
            .bodyToMono(List::class.java)
            .block()?.forEach {
                val responseMap = (it as LinkedHashMap<String, String>)

                val url = URL((responseMap["user"] as LinkedHashMap<String, String>)["avatar_url"])

                val responseModelDTO = ResponseModelDTO(
                    title = responseMap["title"],
                    createDate = responseMap["created_at"],
                    closedDate = responseMap["closed_at"],
                    userName = (responseMap["user"] as LinkedHashMap<String, String>)["login"],
                    userImage = url
                )

                responseListDTO.add(responseModelDTO)
            }
        if (responseListDTO.isEmpty()) {
            throw DataNotFoundException("Pull Requests", "No Closed Pull Request Found")
        }
        logger.info { "fetched list of closed pull request: ${Utils.convertObjectToJsonString(responseListDTO)}" }
        return responseListDTO
    }
}