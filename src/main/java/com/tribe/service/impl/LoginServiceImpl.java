package com.tribe.service.impl;

import com.tribe.dto.LoginRequestDto;
import com.tribe.dto.LoginResponseDto;
import com.tribe.entity.User;
import com.tribe.service.LoginService;
import com.tribe.util.AuthenticationUser;
import com.tribe.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        User user = AuthenticationUser.get(authentication);

        LoginResponseDto loginResponseDto = new LoginResponseDto(user,
                jwtUtil.generateAccessToken(user.getEmail()),
                jwtUtil.generateRefreshToken(user.getEmail()));
        return loginResponseDto;
    }
}
