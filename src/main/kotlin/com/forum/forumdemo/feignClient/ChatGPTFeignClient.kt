package com.forum.forumdemo.feignClient

import com.forum.forumdemo.`object`.ChatRequest
import com.forum.forumdemo.`object`.ChatResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "chatGPT",
    url = "https://api.openai.com/v1/chat/completions"
)
interface ChatGPTFeignClient {
    @GetMapping
    fun sendMsg(@RequestHeader("Authorization") header: String, chatRequest: ChatRequest): ChatResponse
}