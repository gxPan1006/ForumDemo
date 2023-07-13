package com.forum.forumdemo.controller

import com.forum.forumdemo.service.TokenService
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthController(private val tokenService: TokenService) {
    @PostMapping("/token")
    fun token(authentication: Authentication): String {
        logger.debug("Token requested for user: '{}'", authentication.name)
        val token: String = tokenService.generateToken(authentication)
        logger.debug("Token granted: {}", token)
        return token
    }

    companion object {
        private val logger = KotlinLogging.logger {  }
    }
}