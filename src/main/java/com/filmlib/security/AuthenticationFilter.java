package com.filmlib.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.filmlib.util.SecurityUtil.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication
            (
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain chain,
                    Authentication authentication
            ) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        String accessToken = createAccessToken(algorithm, user);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(REFRESH_TOKEN_EXPIRATION))
                .sign(algorithm);

        setTokensToResponse(response, refreshToken, accessToken);
        System.out.printf("User %s logged in", user.getUsername());
    }
}
