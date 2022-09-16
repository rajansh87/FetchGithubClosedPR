package com.example.demo.exception.controller

import com.example.demo.utils.Utils
import com.fasterxml.jackson.annotation.JsonProperty
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.DataBinder
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

data class CustomError(val field: String? = null, val message: String)
data class ErrorResponse(@JsonProperty("code") val code: Int, @JsonProperty("messages") val messages: List<CustomError>)
data class Envelope(@JsonProperty("error") val error: ErrorResponse)

@ResponseStatus(code = HttpStatus.NOT_FOUND)
data class DataNotFoundException(val field: String, override val message: String) : RuntimeException(message)

@RestControllerAdvice
class CommonException {
    private val logger: KLogger = KotlinLogging.logger {}

    @InitBinder
    private fun initDirectFieldAccess(dataBinder: DataBinder) {
        dataBinder.initDirectFieldAccess()
    }

    companion object {
        private fun exceptionFormatter(message: String): String = message.substringAfter("\"").substringBefore("\n")
    }
    @ExceptionHandler(value = [Exception::class])
    fun handleGenericException(exception: Exception): ResponseEntity<Any> {
        return handleExceptionUtil(exception)
    }
    private fun handleExceptionUtil(exception: Exception): ResponseEntity<Any> {
        val error = CustomError(null, exception.message ?: exception.toString())
        logException(exception)
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [DataNotFoundException::class])
    fun handleDataNotFoundException(exception: DataNotFoundException): ResponseEntity<Any> {
        val error = CustomError(exception.field, exception.message)
        logException(exception)
        val envelope = Envelope(ErrorResponse(HttpStatus.NOT_FOUND.value(), listOf(error)))
        val response = ResponseEntity(envelope, HttpStatus.NOT_FOUND)
        logger.error("ERROR Response : ${Utils.convertObjectToJsonString(response)}")
        return response as ResponseEntity<Any>
    }
    private fun logException(exception: Exception) {
        logger.error("Exception Occurred: ${exception.message}", exception)
    }
}
