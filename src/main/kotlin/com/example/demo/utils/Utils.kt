package com.example.demo.utils

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Utils {
    fun <T> convertObjectToJsonString(c: T): String? {
        if (c == null)return null
        return jacksonObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(c)
    }
    fun <T> fromJson(jsonString: String, clazz: Class<T>): T {
        val mapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
        return mapper.readValue(jsonString, clazz)
    }
}