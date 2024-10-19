package studycaseaplikasiapi.springbeaidil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studycaseaplikasiapi.springbeaidil.entity.User;
import studycaseaplikasiapi.springbeaidil.model.UserResponse;
import studycaseaplikasiapi.springbeaidil.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        log.debug("Login request received for user: {}", loginRequest.getUsername());
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        log.debug("Token received from AuthService: {}", token);

        if (token != null && !token.isEmpty()) {
            AuthResponse authResponse = new AuthResponse(token);
            log.debug("Returning OK response with token: {}", token);
            return ResponseEntity.ok(authResponse);
        } else {
            log.warn("Authentication failed for user: {}", loginRequest.getUsername());
            return ResponseEntity.badRequest().body(new AuthResponse("Authentication failed"));
        }
    }
    public static class LoginRequest {
        private String username;
        private String password;

        // getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) { this.token = token; }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }



}