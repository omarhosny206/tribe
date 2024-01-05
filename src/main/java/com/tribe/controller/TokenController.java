package com.tribe.controller;

import com.tribe.dto.TokenRequestDto;
import com.tribe.dto.TokenResponseDto;
import com.tribe.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/")
    public ResponseEntity<TokenResponseDto> signin(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tokenService.regenerateTokens(tokenRequestDto));
    }
}
