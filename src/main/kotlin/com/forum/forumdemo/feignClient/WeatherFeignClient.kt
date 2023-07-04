package com.forum.forumdemo.feignClient

import com.forum.forumdemo.response.WeatherResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(
    name = "weather",
    url = "http://api.weatherapi.com/v1/current.json"
)
interface WeatherFeignClient {
    @GetMapping
    fun getWeather(
        @RequestParam("query") location: String,
        @RequestParam("key") apiKey: String
    ): WeatherResponse
}
