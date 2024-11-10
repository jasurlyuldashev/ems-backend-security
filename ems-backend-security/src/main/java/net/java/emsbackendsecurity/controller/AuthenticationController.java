package net.java.emsbackendsecurity.controller;

import lombok.RequiredArgsConstructor;
import net.java.emsbackendsecurity.dto.JwtAuthResponse;
import net.java.emsbackendsecurity.dto.RefreshTokenRequest;
import net.java.emsbackendsecurity.dto.SignInRequest;
import net.java.emsbackendsecurity.dto.SignUpRequest;
import net.java.emsbackendsecurity.entity.User;
import net.java.emsbackendsecurity.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest singUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(singUpRequest));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
