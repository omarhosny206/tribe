package com.tribe.service.impl;


import com.tribe.dto.LoginRequestDto;
import com.tribe.dto.LoginResponseDto;
import com.tribe.entity.User;
import com.tribe.util.AuthenticationUser;
import com.tribe.util.CustomUser;
import com.tribe.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class LoginServiceTest {
    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationUser authenticationUser;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String hashedPassword;
    private String accessToken;
    private String refreshToken;
    private User savedUser;


    @BeforeEach
    public void setUp() {
        openMocks(this);

        id = 1L;
        firstName = "omar";
        lastName = "hosny";
        email = "omarhosny102@gmail.com";
        username = "omarhosny102";
        password = "12345678";
        hashedPassword = "####12345678####";
        accessToken = "access-token";
        refreshToken = "refresh-token";
        savedUser = new User(id, firstName, lastName, username, email, hashedPassword);
    }

    @Test
    public void shouldThrowExceptionWhenSendingBadCredentials() {
        // Arrange
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(BadCredentialsException.class);

        LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);

        // Act
        Throwable throwable = catchThrowable(() -> loginService.login(loginRequestDto));

        // Assert
        assertThat(throwable)
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    public void shouldLoginSuccessfully() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(getAuthentication());

        try (MockedStatic<AuthenticationUser> mocked = mockStatic(AuthenticationUser.class)) {
            mocked.when(() -> AuthenticationUser.get(any(Authentication.class)))
                    .thenReturn(savedUser);
        }

        when(jwtUtil.generateAccessToken(anyString()))
                .thenReturn(accessToken);

        when(jwtUtil.generateRefreshToken(anyString()))
                .thenReturn(refreshToken);

        LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);

        // Act
        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);

        // Assert
        assertThat(loginResponseDto.getUser())
                .usingRecursiveAssertion()
                .isEqualTo(savedUser);

        assertThat(loginResponseDto.getAccessToken())
                .isEqualTo(accessToken);

        assertThat(loginResponseDto.getRefreshToken())
                .isEqualTo(refreshToken);
    }

    private Authentication getAuthentication() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public Object getCredentials() {
                return new Object();
            }

            @Override
            public Object getDetails() {
                return new Object();
            }

            @Override
            public Object getPrincipal() {
                return new CustomUser(savedUser);
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                isAuthenticated = true;
            }

            @Override
            public String getName() {
                return "";
            }
        };
    }
}