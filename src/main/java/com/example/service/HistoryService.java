package com.example.service;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.security.Principal;

public interface HistoryService {
    @Transactional
    public ResponseEntity<?> clear(Principal principal);
}
