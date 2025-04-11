package com.tribe.service.impl;


import com.tribe.dto.SignupRequestDto;
import com.tribe.dto.TribeRequestDto;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;
import com.tribe.exception.CustomException;
import com.tribe.util.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignupServiceTest {
    @Mock
    private UserServiceImpl userService;

    @Mock
    private TribeServiceImpl tribeService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private SignupServiceImpl signupService;

    private String generatedUsername;
    private String tribeNamePrefix;
    private User user;
    private SignupRequestDto signupRequestDto;
    private Tribe tribe;

    @BeforeEach
    public void setUp() {
        generatedUsername = "omarhosny102999";

        long id = 1L;
        String firstName = "omar";
        String lastName = "hosny";
        String email = "omarhosny102@gmail.com";
        String username = generatedUsername;
        String hashedPassword = "####12345678####";
        user = new User(id, firstName, lastName, username, email, hashedPassword);
        signupRequestDto = new SignupRequestDto(firstName, lastName, email, hashedPassword);
        tribe = new Tribe("own.tribe." + user.getId(), user);
    }

    @Test
    public void shouldSignupSuccessfully() {
        // Arrange
        when(userService.getByEmailOrNull(anyString()))
                .thenReturn(null);

        try (MockedStatic<UsernameGenerator> mocked = mockStatic(UsernameGenerator.class)) {
            mocked.when(() -> UsernameGenerator.generateFromEmail(anyString())).thenReturn(generatedUsername);
        }

        when(userService.getByUsernameOrNull(anyString()))
                .thenReturn(null);

        when(passwordEncoder.encode(anyString()))
                .thenReturn(user.getPassword());

        when(userService.save(any(User.class)))
                .thenReturn(user);

        when(tribeService.save(any(User.class), any(TribeRequestDto.class)))
                .thenReturn(tribe);

        // Act
        User user = signupService.signup(signupRequestDto);

        // Assert
        assertThat(user)
                .usingRecursiveAssertion()
                .isEqualTo(user);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsAlreadyUsed() {
        // Arrange
        when(userService.getByEmailOrNull(anyString()))
                .thenReturn(user);

        // Act
        Throwable throwable = catchThrowable(() -> signupService.signup(signupRequestDto));

        // Assert
        assertThat(throwable)
                .isInstanceOf(CustomException.class);

        assertThat(((CustomException) throwable).getHttpStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}