package com.tribe.dto;

import com.tribe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private User user;
    private String accessToken;
    private String refreshToken;
}