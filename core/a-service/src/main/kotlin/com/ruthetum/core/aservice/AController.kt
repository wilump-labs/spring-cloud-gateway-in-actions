package com.ruthetum.core.aservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AController {
    @GetMapping("/a")
    fun onRequest(): String {
        return "This is A service"
    }
}