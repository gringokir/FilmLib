package com.filmlib.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmlib.entity.User;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class SecurityUtil {

    public static String SECRET_KEY = "secret";
    public static String BEARER = "Bearer ";
    public static long ACCESS_TOKEN_EXPIRATION = LocalDate.now()
            .plusWeeks(2)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();
    public static long REFRESH_TOKEN_EXPIRATION = LocalDate.now()
            .plusYears(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli();

    public static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static void tokenException(HttpServletResponse response, Exception e) throws IOException {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }

    public static String getUsername(String refreshToken) {
        JWTVerifier jwtVerifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
        return decodedJWT.getSubject();
    }

    public static String createAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("user_id", user.getId())
                .withExpiresAt(new Date(ACCESS_TOKEN_EXPIRATION))
                .withClaim(
                        "roles",
                        user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
                .sign(ALGORITHM);
    }

    public static String getRefreshToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("user_id", user.getId())
                .withExpiresAt(new Date(REFRESH_TOKEN_EXPIRATION))
                .sign(ALGORITHM);
    }

    public static void setTokensToResponse(HttpServletResponse response, String refreshToken, String accessToken) {
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
    }
}
