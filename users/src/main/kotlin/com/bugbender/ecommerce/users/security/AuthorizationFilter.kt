package com.bugbender.ecommerce.users.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    environment: Environment
) : BasicAuthenticationFilter(authenticationManager) {

    private val tokenSecret = environment.getProperty("token.secret")!!
    private val secretKey = Keys.hmacShaKeyFor(tokenSecret.toByteArray())

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            chain.doFilter(request, response)
            return
        }
        val token = getAuthenticationToken(authorizationHeader)
        SecurityContextHolder.getContext().authentication = token

        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(authHeader: String): UsernamePasswordAuthenticationToken? = try {
        val jwt = authHeader.substringAfter("Bearer ")
        val userId = extractSubject(jwt)
        val authorities = extractAuthorities(jwt)

        if (!userId.isNullOrBlank()) {
            val principal = userId
            UsernamePasswordAuthenticationToken(principal, null, authorities)
        } else
            null
    } catch (e: Exception) {
        null
    }

    private fun extractSubject(token: String): String? = getAllClaims(token).subject

    private fun getAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token).payload
    }

    private fun extractAuthorities(token: String): List<SimpleGrantedAuthority> {
        val claims = getAllClaims(token)
        val scope = claims.get("scope", List::class.java) as List<Map<String, String>>
        return scope.map { SimpleGrantedAuthority(it["authority"]) }
    }
}