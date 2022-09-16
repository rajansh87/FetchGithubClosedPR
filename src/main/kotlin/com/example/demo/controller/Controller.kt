package com.example.demo.controller

import com.example.demo.model.ResponseModelDTO
import com.example.demo.service.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@org.springframework.stereotype.Controller
@RequestMapping("/list")
class Controller(private val service: Service) {

    @GetMapping("/pull-request/closed")
    @ResponseBody
    fun getAllClosedPR(): MutableList<ResponseModelDTO>? {
        return service.getAllClosedPRs()
    }
}