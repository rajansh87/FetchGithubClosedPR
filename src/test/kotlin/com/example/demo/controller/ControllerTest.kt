package com.example.demo.controller

import com.example.demo.service.ServiceImpl
import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.*
import io.mockk.impl.annotations.InjectMockKs

import io.mockk.every
import org.springframework.http.HttpStatus
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {

    @MockK
    lateinit var service: ServiceImpl

    @InjectMockKs
    lateinit var controller: Controller

    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)


}