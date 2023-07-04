package com.forum.forumdemo.`object`

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class ChatResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage
)

@JsonNaming(SnakeCaseStrategy::class)
data class Choice(
    val index: Int,
    val message: Message,
    val finishReason: String
)

data class Message(
    val role: String,
    val content: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class Usage(
    val promptTokens: Int,
    val completionTokens: Int,
    val totalTokens: Int
)
