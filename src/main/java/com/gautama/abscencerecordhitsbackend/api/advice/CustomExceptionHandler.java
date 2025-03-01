package com.gautama.abscencerecordhitsbackend.api.advice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception e) {
        ProblemDetail errorDetail = null;

        if (e instanceof BadCredentialsException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        }

        if (e instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "Not Authorized");
        }

        if (e instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature Error");
        }

        if (e instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token Expired");
        }
        return errorDetail;
    }



}