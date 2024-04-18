package com.bugbender.ecommerce.discovery

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurity() {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        http.csrf { csrfCustomizer ->
            csrfCustomizer.disable()
        }

        http.authorizeHttpRequests { auth ->
            auth.anyRequest().authenticated()
        }
        http.httpBasic(Customizer.withDefaults())
        return http.build()
    }
}