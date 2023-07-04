package com.forum.forumdemo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorizeHttpRequests ->
                authorizeHttpRequests
                    .anyRequest().permitAll()
            }
            .build()
}


