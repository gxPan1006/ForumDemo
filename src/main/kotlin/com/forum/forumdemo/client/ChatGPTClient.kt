package com.forum.forumdemo.client

import com.forum.forumdemo.feignClient.ChatGPTFeignClient
import com.forum.forumdemo.`object`.ChatRequest
import org.springframework.stereotype.Component

private const val GPT_HEADER = "Bearer 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"

@Component
class ChatGPTClient(private val chatGPTFeignClient: ChatGPTFeignClient) {
    fun sendMsg(chatRequest: ChatRequest): String =
        chatGPTFeignClient.sendMsg(GPT_HEADER, chatRequest).choices.map { it.message.content }.last()
}