package com.example.service;

import com.example.dto.TribeDto;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface TribeService {
    public ResponseEntity<?> create(Principal principal, TribeDto tribeDto);
}
