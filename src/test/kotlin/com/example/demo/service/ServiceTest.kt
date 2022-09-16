package com.example.demo.service

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceTest {

    @InjectMockKs
    lateinit var service: ServiceImpl

    @BeforeAll
    fun setUp() = MockKAnnotations.init(this)


}