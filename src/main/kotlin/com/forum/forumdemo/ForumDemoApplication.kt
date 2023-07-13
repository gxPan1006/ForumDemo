package com.forum.forumdemo

import com.forum.forumdemo.configuration.RsaKeyProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyProperties::class)
class ForumDemoApplication

fun main(args: Array<String>) {
    runApplication<ForumDemoApplication>(*args)
}
