package com.bugbender.ecommerce.users.security

import com.bugbender.ecommerce.users.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val environment: Environment,
    private val encoder: BCryptPasswordEncoder,
    private val userService: UserService
) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder
            .userDetailsService(userService)
            .passwordEncoder(encoder)

        val authenticationManager = authenticationManagerBuilder.build()

        val authenticationFilter = AuthenticationFilter(authenticationManager, userService, environment)
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"))

        http.csrf { csrfCustomizer ->
            csrfCustomizer.disable()
        }

        http.authorizeHttpRequests { auth ->
            auth
                // .requestMatchers(HttpMethod.POST, "api/users", "api/users/login").permitAll()
                .requestMatchers("/api/users/**")
                .access(WebExpressionAuthorizationManager("hasIpAddress('${environment.getProperty("gateway.ip")}')"))
        }
            .addFilter(authenticationFilter)
            .authenticationManager(authenticationManager)


        http.sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.headers { headersCustomizer ->
            headersCustomizer.frameOptions { frameOptionsCustomizer ->
                frameOptionsCustomizer.disable()
            }
        }

        return http.build()
    }
}