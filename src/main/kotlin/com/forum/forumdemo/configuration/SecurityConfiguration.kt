package com.forum.forumdemo.configuration

import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val rsaKeys: RsaKeyProperties) {

    @Bean
    fun user(): InMemoryUserDetailsManager = InMemoryUserDetailsManager(
        User.withUsername("pan").password("{noop}1234").authorities("read").build()
    )

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
//                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { oauth2ResourceServer ->
                oauth2ResourceServer.jwt {}
            }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .formLogin(org.springframework.security.config.Customizer.withDefaults()) // use login [age
            .httpBasic(withDefaults())  // use login popup
            .build()
    }

    @Bean
    fun JwtDecoder(): JwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey).build()

    @Bean
    fun jwtEncoder(): JwtEncoder? {
        val jwk: JWK = com.nimbusds.jose.jwk.RSAKey.Builder(rsaKeys.publicKey).privateKey(rsaKeys.privateKey).build()
        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }

}


