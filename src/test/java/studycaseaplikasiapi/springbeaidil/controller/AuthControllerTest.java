package studycaseaplikasiapi.springbeaidil.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import studycaseaplikasiapi.springbeaidil.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        // Arrange
        String username = "testuser";
        String password = "testpass";
        String expectedToken = "test-token";
        AuthController.LoginRequest loginRequest = new AuthController.LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        when(authService.login(username, password)).thenReturn(expectedToken);

        // Act
        ResponseEntity<AuthController.AuthResponse> response = authController.login(loginRequest);

        // Assert
        log.debug("Response status: {}", response.getStatusCode());
        log.debug("Response body: {}", response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        AuthController.AuthResponse authResponse = response.getBody();
        assertNotNull(authResponse, "Auth response should not be null");
        assertEquals(expectedToken, authResponse.getToken(), "Tokens should match");

        verify(authService, times(1)).login(username, password);
    }

    @Test
    void testLoginFailure() {
        // Arrange
        String username = "wronguser";
        String password = "wrongpass";
        AuthController.LoginRequest loginRequest = new AuthController.LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        when(authService.login(username, password)).thenReturn(null);

        // Act
        ResponseEntity<AuthController.AuthResponse> response = authController.login(loginRequest);

        // Assert
        log.debug("Response status: {}", response.getStatusCode());
        log.debug("Response body: {}", response.getBody());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status should be BAD_REQUEST");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("Authentication failed", response.getBody().getToken(), "Should return authentication failed message");

        verify(authService, times(1)).login(username, password);
    }
}