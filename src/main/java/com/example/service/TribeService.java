package com.example.service;

import com.example.dto.TribeDto;
import com.example.entity.Tribe;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface TribeService {
    public ResponseEntity<?> create(Principal principal, TribeDto tribeDto);

    Tribe getByName(String name);
}
