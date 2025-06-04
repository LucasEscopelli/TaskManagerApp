package com.app.taskmanager.security.login;

import static org.assertj.core.api.Assertions.*;

import com.app.taskmanager.service.security.login.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;


@Tag("unit")
@DisplayName("Testes unitários para TokenUtils")
public class TokenUtilsTest {
    @Test
    @DisplayName("Gera token não vazio")
    void generateToken_shouldReturnNonEmptyToken() {
        String username = "user123";
        String token = TokenUtils.generateToken(username);

        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    @DisplayName("Parse token retorna username correto")
    void parseToken_shouldReturnOriginalUsername() {
        String username = "user123";
        String token = TokenUtils.generateToken(username);

        String parsedUsername = TokenUtils.parseToken(token);

        assertThat(parsedUsername).isEqualTo(username);
    }

    @Test
    @DisplayName("Valida token e retorna Claims com subject correto")
    void validateToken_shouldReturnClaimsWithCorrectSubject() throws java.security.SignatureException {
        String username = "user123";
        String token = TokenUtils.generateToken(username);

        Claims claims = TokenUtils.validateToken(token);

        assertThat(claims).isNotNull();
        assertThat(claims.getSubject()).isEqualTo(username);
    }

    @Test
    @DisplayName("Validação de token inválido lança JwtException")
    void validateToken_withInvalidToken_shouldThrowJwtException() {
        String invalidToken = "this.is.an.invalid.token";

        assertThatThrownBy(() -> TokenUtils.validateToken(invalidToken))
                .isInstanceOf(JwtException.class);
    }
}
