package com.bugbender.ecommerce.users.security

import com.bugbender.ecommerce.users.model.request.LoginRequestModel
import com.bugbender.ecommerce.users.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.Instant
import java.util.*

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val environment: Environment
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    private val tokenSecret = environment.getProperty("token.secret")!!
    private val secretKey = Keys.hmacShaKeyFor(tokenSecret.toByteArray())

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credential: LoginRequestModel = ObjectMapper().readValue(request.inputStream, LoginRequestModel::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                credential.email,
                credential.password,
                ArrayList()
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val userEmail = (authResult.principal as User).username
        val userDto = userService.getUserDetailsByUsername(userEmail)
        val expirationTime = environment.getProperty("token.expiration_time")!!.toLong()
        val authorities = authResult.authorities
        val now = Instant.now()
        val token = Jwts.builder()
            .claim("scope", authResult.authorities)
            .subject(userDto.userId)
            .expiration(Date.from(now.plusMillis(expirationTime)))
            .issuedAt(Date.from(now))
            .signWith(secretKey)
            .compact()

        response.addHeader("token", token)
        response.addHeader("userId", userDto.userId)
    }
}