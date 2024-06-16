package com.ruthetum.core.bservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BController {
    @GetMapping("/b")
    fun onRequest(): String {
        return "This is B service"
    }

    @GetMapping("/c")
    fun onRequest2(): String {
        return "This is C in B service"
    }
}