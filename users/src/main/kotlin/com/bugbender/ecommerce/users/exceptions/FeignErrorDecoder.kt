package com.bugbender.ecommerce.users.exceptions

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignErrorDecoder(private val environment: Environment) : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception = when (response.status()) {
        404 -> ResponseStatusException(
            HttpStatus.valueOf(response.status()),
            if (methodKey.contains("getProducts")) "User products are not found" else response.reason()
        )

        else -> RuntimeException(response.reason())
    }
}