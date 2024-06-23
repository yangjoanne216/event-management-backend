package com.dauphine.eventmanagement.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
  private final UserAuthenticationProvider userAuthenticationProvider;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);

    logger.info("Processing authentication for request: {}", request.getRequestURI());

    if (header != null) {
      String[] authElements = header.split(" ");

      if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
        try {
          if ("GET".equals(request.getMethod())) {
            SecurityContextHolder.getContext().setAuthentication(
                userAuthenticationProvider.validateToken(authElements[1]));
          } else {
            SecurityContextHolder.getContext()
                .setAuthentication(userAuthenticationProvider.validateStrongerToken(
                    authElements[1])); // double check
          }
          logger.info("Authentication successful for token: {}", authElements[1]);
        } catch (RuntimeException e) {
          SecurityContextHolder.clearContext();
          logger.error("Authentication failed for token: {}", authElements[1], e);
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication Failed");
          return;
        }
      } else {
        logger.warn("Authorization header format is invalid.");
      }
    } else {
      logger.warn("Authorization header is missing.");
    }

    filterChain.doFilter(request, response);
  }
  
}