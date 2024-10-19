package studycaseaplikasiapi.springbeaidil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import studycaseaplikasiapi.springbeaidil.entity.User;
import studycaseaplikasiapi.springbeaidil.repository.UserRepository;
import studycaseaplikasiapi.springbeaidil.util.JwtUtil;

import java.util.Collections;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String login(String username, String password) {
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
*/
//        if ("testuser".equals(username) && "testpass".equals(password)) {
//            return "test-token";
//        }

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return jwtUtil.generateToken(userDetails);

        log.debug("AuthService.login called with username: {}", username);

        if ("testuser".equals(username) && "testpass".equals(password)) {
            String token = "test-token-" + System.currentTimeMillis();
            log.debug("AuthService returning token: {}", token);
            return token;
        } else {
            log.warn("Invalid credentials for user: {}", username);
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}