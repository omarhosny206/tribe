package com.tribe.service.impl;

import com.tribe.dto.TokenRequestDto;
import com.tribe.dto.TokenResponseDto;
import com.tribe.util.JwtUtil;
import com.tribe.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final JwtUtil jwtUtil;

    public TokenServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenResponseDto regenerateTokens(TokenRequestDto tokenRequestDto) {
        Claims claims = jwtUtil.verifyRefreshToken(tokenRequestDto.getRefreshToken());
        String email = jwtUtil.getSubject(claims);

        TokenResponseDto tokenResponseDto = new TokenResponseDto(jwtUtil.generateAccessToken(email),
                jwtUtil.generateRefreshToken(email));
        return tokenResponseDto;
    }
}
