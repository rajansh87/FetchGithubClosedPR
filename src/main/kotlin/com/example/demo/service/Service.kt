package com.example.demo.service

import com.example.demo.model.ResponseModelDTO
import java.awt.Image
import javax.servlet.ServletContext


interface Service {
    fun getAllClosedPRs():MutableList<ResponseModelDTO>?
}
