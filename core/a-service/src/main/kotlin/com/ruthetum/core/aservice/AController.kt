package com.ruthetum.core.aservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AController {
    @GetMapping("/a")
    fun onRequest(): String {
        return "This is A service"
    }

    @GetMapping("/c")
    fun onRequest2(): String {
        return "This is C in A service"
    }
}