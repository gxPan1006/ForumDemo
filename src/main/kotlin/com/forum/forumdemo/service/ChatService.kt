package com.forum.forumdemo.service

import com.forum.forumdemo.client.ChatGPTClient
import com.forum.forumdemo.`object`.ChatRequest
import org.springframework.stereotype.Service


@Service
class ChatService(private val chatClient: ChatGPTClient) {
    fun sendMsg(chatRequest: ChatRequest) = chatClient.sendMsg(chatRequest)
}