package net.java.emsbackendsecurity.service;

import net.java.emsbackendsecurity.dto.JwtAuthResponse;
import net.java.emsbackendsecurity.dto.RefreshTokenRequest;
import net.java.emsbackendsecurity.dto.SignInRequest;
import net.java.emsbackendsecurity.dto.SignUpRequest;
import net.java.emsbackendsecurity.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest singUpRequest);

    JwtAuthResponse signIn(SignInRequest signInRequest);
    JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
