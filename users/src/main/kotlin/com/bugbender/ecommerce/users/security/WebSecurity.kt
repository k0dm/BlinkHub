package com.bugbender.ecommerce.users.security

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val environment: Environment
) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
//        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
//        authenticationManagerBuilder
//            .userDetailsService(usersService)
//            .passwordEncoder(bCryptPasswordEncoder())
//
//        val authenticationManager = authenticationManagerBuilder.build()
//
//        //create authenticationFilter
//        val authenticationFilter = AuthenticationFilter(authenticationManager)
//        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"))

        http.csrf { csrfCustomizer ->
            csrfCustomizer.disable()
        }

        http.authorizeHttpRequests { auth ->
            auth
                .requestMatchers(HttpMethod.POST, "api/users").permitAll()
                //.requestMatchers("api/users/status", "api/users/login").permitAll()
                .requestMatchers("api/users/**")
                .access(WebExpressionAuthorizationManager(("hasIpAddress('" + environment.getProperty("gateway.ip")) + "')"))
            //          .anyRequest()
            //         .authenticated()
        }
        //  .addFilter(authenticationFilter)
        // .authenticationManager(authenticationManager)


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