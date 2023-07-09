package com.forum.forumdemo.controller

import com.forum.forumdemo.`object`.ChatRequest
import com.forum.forumdemo.request.LoginInfo
import com.forum.forumdemo.response.WeatherResponse
import com.forum.forumdemo.service.ChatService
import com.forum.forumdemo.service.WeatherService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
class HomePageController(
    private val weatherService: WeatherService,
    private val chatService: ChatService
) {
    @GetMapping("/")
    fun home(): String {
        return "homePage"
    }

    @GetMapping("/weather")
    fun weather(): WeatherResponse = weatherService.getWeather("Paris", "d3c80ddc93fd4e06ae072450230406")

    @PostMapping("/chat")
    fun chat(
        @RequestBody chatRequest: ChatRequest
    ): String {
        return chatService.sendMsg(chatRequest)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginInfo: LoginInfo) =
        println("==============================================${loginInfo.username} login!!")
}
