package com.tribe.service;

import com.tribe.dto.TokenRequestDto;
import com.tribe.dto.TokenResponseDto;

public interface TokenService {
    TokenResponseDto regenerateTokens(TokenRequestDto tokenRequestDto);
}
