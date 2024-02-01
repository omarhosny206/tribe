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

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String hashedPassword;
    private String accessToken;
    private String refreshToken;
    private User user;
    private String tribeNamePrefix;

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
        user = new User(id, firstName, lastName, username, email, hashedPassword);
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
        when(userService.getByEmailOrNull(anyString()))
                .thenReturn(null);

        try (MockedStatic<UsernameGenerator> mocked = mockStatic(UsernameGenerator.class)) {
            mocked.when(() -> UsernameGenerator.generateFromEmail(anyString())).thenReturn(username);
        }

        when(userService.getByUsernameOrNull(anyString()))
                .thenReturn(null);

        when(passwordEncoder.encode(anyString()))
                .thenReturn(null);

        SignupRequestDto signupRequestDto = new SignupRequestDto(firstName, lastName, email, password);
        User savedUser = new User(id, firstName, lastName, username, email, hashedPassword);
        Tribe tribe = new Tribe(tribeNamePrefix + savedUser.getId(), savedUser);

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