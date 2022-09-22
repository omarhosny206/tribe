package com.example.service;

import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

public interface HistoryService {
    @Transactional
    public ResponseEntity<?> clear(Principal principal);

    public ResponseEntity<List<String>> getAll(Principal principal);
}
