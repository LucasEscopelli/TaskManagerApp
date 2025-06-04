package com.app.taskmanager.security.login;

import static org.mockito.Mockito.*;

import com.app.taskmanager.service.security.login.JwtAuthenticationFilter;
import com.app.taskmanager.service.security.login.TokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter filter;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        filter = new JwtAuthenticationFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);

        // Limpar o contexto de segurança antes de cada teste
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternalWithValidToken() throws Exception {
        String token = "valid.token.here";
        String username = "testuser";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        Claims claims = mock(Claims.class);
        when(claims.getSubject()).thenReturn(username);

        try (MockedStatic<TokenUtils> mockedStatic = Mockito.mockStatic(TokenUtils.class)) {
            mockedStatic.when(() -> TokenUtils.validateToken(token)).thenReturn(claims);

            filter.doFilter(request, response, filterChain);

            assertNotNull(SecurityContextHolder.getContext().getAuthentication());
            assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            verify(filterChain).doFilter(request, response);
        }
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws Exception {
        String token = "invalid.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        try (MockedStatic<TokenUtils> mockedStatic = Mockito.mockStatic(TokenUtils.class)) {
            mockedStatic.when(() -> TokenUtils.validateToken(token))
                    .thenThrow(new RuntimeException("Invalid token"));

            filter.doFilter(request, response, filterChain);

            verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            verify(filterChain, never()).doFilter(request, response);
            assertNull(SecurityContextHolder.getContext().getAuthentication());
        }
    }

    @Test
    void testDoFilterInternalWithoutAuthorizationHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
