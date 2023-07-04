package com.forum.forumdemo.service

import com.forum.forumdemo.response.WeatherResponse
import com.forum.forumdemo.client.WeatherClient
import org.springframework.stereotype.Service

@Service
class WeatherService(private val weatherClient: WeatherClient) {
    fun getWeather(location: String, apiKey: String): WeatherResponse = weatherClient.getWeather(location, apiKey)
}
