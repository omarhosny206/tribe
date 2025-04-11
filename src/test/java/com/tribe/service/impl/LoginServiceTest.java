package com.tribe.service.impl;


import com.tribe.dto.LoginRequestDto;
import com.tribe.dto.LoginResponseDto;
import com.tribe.entity.User;
import com.tribe.util.CustomUser;
import com.tribe.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginServiceImpl loginService;

    private String accessToken;
    private String refreshToken;
    private User user;
    private CustomUser customUser;
    private LoginRequestDto loginRequestDto;



    @BeforeEach
    public void setUp() {
        long id = 1L;
        String firstName = "omar";
        String lastName = "hosny";
        String email = "omarhosny102@gmail.com";
        String username = "omarhosny102";
        String hashedPassword = "####12345678####";
        accessToken = "access-token";
        refreshToken = "refresh-token";
        user = new User(id, firstName, lastName, username, email, hashedPassword);
        customUser = new CustomUser(user);
        loginRequestDto = new LoginRequestDto(user.getEmail(), user.getPassword());
    }

    @Test
    public void shouldLoginSuccessfully() {
        // Arrange
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(authentication.getPrincipal()).thenReturn(customUser);

        when(jwtUtil.generateAccessToken(anyString()))
                .thenReturn(accessToken);

        when(jwtUtil.generateRefreshToken(anyString()))
                .thenReturn(refreshToken);

        // Act
        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);

        // Assert
        assertThat(loginResponseDto.getUser())
                .usingRecursiveAssertion()
                .isEqualTo(user);

        assertThat(loginResponseDto.getAccessToken())
                .isEqualTo(accessToken);

        assertThat(loginResponseDto.getRefreshToken())
                .isEqualTo(refreshToken);
    }

    @Test
    public void shouldThrowExceptionWhenBadCredentials() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        // Act
        Throwable throwable = catchThrowable(() -> loginService.login(loginRequestDto));

        // Assert
        assertThat(throwable)
                .isInstanceOf(BadCredentialsException.class)
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    public void shouldThrowExceptionWhenAuthenticationIsNull() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);

        // Act
        Throwable throwable = catchThrowable(() -> loginService.login(loginRequestDto));

        // Assert
        assertThat(throwable)
                .isInstanceOf(NullPointerException.class);
    }
}