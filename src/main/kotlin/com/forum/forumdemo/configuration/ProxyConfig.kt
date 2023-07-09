package com.forum.forumdemo.configuration

import feign.Feign
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.Socket

//
//@Configuration
//class FeignConfig {
//    @Bean
//    fun feignBuilder(): Feign.Builder {
//        val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 7890))
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .proxy(proxy)
//            .build()
//        return Feign.builder()
//            .client(feign.okhttp.OkHttpClient(client))
//    }
//}

@Configuration
class FeignConfig {

    @Bean
    fun feignBuilder(): Feign.Builder {
        val proxyHost = "localhost"
        val proxyPort = 7890

        return if (isPortInUse(proxyHost, proxyPort)) {
            val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyHost, proxyPort))
            val client: OkHttpClient = OkHttpClient.Builder()
                .proxy(proxy)
                .build()
            Feign.builder()
                .client(feign.okhttp.OkHttpClient(client))
        } else {
            Feign.builder()
        }
    }

    private fun isPortInUse(host: String, port: Int): Boolean {
        return try {
            Socket(host, port).use { it.isConnected }
        } catch (e: Exception) {
            false
        }
    }
}

