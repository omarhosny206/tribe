package com.tribe.service;

import com.tribe.dto.LoginRequestDto;
import com.tribe.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
