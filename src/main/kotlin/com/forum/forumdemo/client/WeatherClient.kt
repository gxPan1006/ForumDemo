package com.forum.forumdemo.client

import com.forum.forumdemo.response.WeatherResponse
import com.forum.forumdemo.feignClient.WeatherFeignClient
import org.springframework.stereotype.Component

@Component
class WeatherClient(private val weatherFeignClient: WeatherFeignClient) {
    fun getWeather(location: String, apiKey: String): WeatherResponse = weatherFeignClient.getWeather(location, apiKey)
}
