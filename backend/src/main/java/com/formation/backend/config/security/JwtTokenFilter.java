package com.formation.backend.config.security;

import com.formation.backend.exception.CustomJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * JwtTokenFilter class is used to intercept the incoming requests
 * and validate the JWT token
 * */
public class JwtTokenFilter extends OncePerRequestFilter {

    // Autowire the JwtTokenProvider bean
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * JwtTokenFilter constructor
     *
     * @param jwtTokenProvider JwtTokenProvider bean
     */
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * This method is used to intercept the incoming requests
     * and validate the JWT token
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        String requestURI = request.getRequestURI();

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (CustomJwtException e) {
            if ("TokenExpired".equals(e.getReason())) {
                if (isPrivateRoute(requestURI)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("{\"reason\": \"TokenExpired\"}");
                    return;
                }
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"reason\": \"InvalidToken\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPrivateRoute(String uri) {
        List<String> privateRoutes = List.of("/api/users/**", "/api/admin/**");

        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String route : privateRoutes) {
            if (pathMatcher.match(route, uri)) {
                return true;
            }
        }

        return false;
    }


    /**
     * This method is used to get the JWT token from the request
     *
     * @param request the incoming request
     * @return JWT token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
