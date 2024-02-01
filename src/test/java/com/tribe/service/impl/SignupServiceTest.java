package com.tribe.service.impl;


import com.tribe.dto.SignupRequestDto;
import com.tribe.entity.Tribe;
import com.tribe.entity.User;
import com.tribe.exception.CustomException;
import com.tribe.service.TribeService;
import com.tribe.service.UserService;
import com.tribe.util.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SignupServiceTest {
    @InjectMocks
    private SignupServiceImpl signupService;

    @Mock
    private UserService userService;

    @Mock
    private TribeService tribeService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsernameGenerator usernameGenerator;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsAlreadyUsed() {
        // Arrange
        User user = new User();
        when(userService.getByEmailOrNull(anyString()))
                .thenReturn(user);

        SignupRequestDto signupRequestDto = new SignupRequestDto("omar", "hosny", "omarhosny102@gmail.com", "12345678");

        // Act
        Throwable throwable = catchThrowable(() -> signupService.signup(signupRequestDto));

        // Assert
        assertThat(throwable)
                .isInstanceOf(CustomException.class);

        assertThat(((CustomException) throwable).getHttpStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldSignupSuccessfully() {
        // Arrange
        final Long ID = 1L;
        final String FIRST_NAME = "omar";
        final String SECOND_NAME = "hosny";
        final String EMAIL = "omarhosny102@gmail.com";
        final String USERNAME = "omarhosny102";
        String PASSWORD = "12345678";
        String HASHED_PASSWORD = "####12345678####";

        when(userService.getByEmailOrNull(anyString()))
                .thenReturn(null);

        try (MockedStatic<UsernameGenerator> mocked = mockStatic(UsernameGenerator.class)) {
            mocked.when(() -> UsernameGenerator.generateFromEmail(anyString())).thenReturn(USERNAME);
        }

        when(userService.getByUsernameOrNull(anyString()))
                .thenReturn(null);

        when(passwordEncoder.encode(anyString()))
                .thenReturn(null);

        SignupRequestDto signupRequestDto = new SignupRequestDto(FIRST_NAME, SECOND_NAME, EMAIL, PASSWORD);
        User savedUser = new User(ID, FIRST_NAME, SECOND_NAME, USERNAME, EMAIL, HASHED_PASSWORD);
        Tribe tribe = new Tribe("own.tribe." + savedUser.getId(), savedUser);

        when(userService.save(any(User.class)))
                .thenReturn(savedUser);

        when(tribeService.save(any(Tribe.class)))
                .thenReturn(tribe);

        // Act
        User user = signupService.signup(signupRequestDto);

        // Assert
        assertThat(user)
                .usingRecursiveAssertion()
                .isEqualTo(savedUser);
    }
}