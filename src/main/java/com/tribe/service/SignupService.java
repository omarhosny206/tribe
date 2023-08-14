package com.tribe.service;

import com.tribe.dto.SignupRequestDto;
import com.tribe.entity.User;

public interface SignupService {
    User signup(SignupRequestDto signupRequestDto);
}
