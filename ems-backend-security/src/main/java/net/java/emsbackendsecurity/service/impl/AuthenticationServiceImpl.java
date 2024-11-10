package net.java.emsbackendsecurity.service.impl;

import lombok.RequiredArgsConstructor;

import net.java.emsbackendsecurity.dto.JwtAuthResponse;
import net.java.emsbackendsecurity.dto.RefreshTokenRequest;
import net.java.emsbackendsecurity.dto.SignInRequest;
import net.java.emsbackendsecurity.dto.SignUpRequest;
import net.java.emsbackendsecurity.entity.Role;
import net.java.emsbackendsecurity.entity.User;
import net.java.emsbackendsecurity.repository.UserRepository;
import net.java.emsbackendsecurity.service.AuthenticationService;
import net.java.emsbackendsecurity.service.JWTService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Qualifier("JWTService")
    private final JWTService jwtService;

    public User signup(SignUpRequest singUpRequest) {
        User user = new User();
        user.setEmail(singUpRequest.getEmail());
        user.setFirstName(singUpRequest.getFirstName());
        user.setLastName(singUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
        return userRepository.save(user);
    }
    public JwtAuthResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;

    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String email = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = (User) userRepository.findByEmail(email);
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponse;
        }
        return null;
    }
}
