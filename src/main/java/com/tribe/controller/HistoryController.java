package com.tribe.controller;

import com.tribe.dto.ContentDto;
import com.tribe.dto.MessageDto;
import com.tribe.entity.History;
import com.tribe.service.HistoryService;
import com.tribe.util.AuthenticationUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/histories")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/")
    public ResponseEntity<List<History>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(historyService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<History>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(historyService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @PostMapping("/")
    public ResponseEntity<History> save(Authentication authentication, @Valid @RequestBody ContentDto contentDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(historyService.save(AuthenticationUser.get(authentication), contentDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<MessageDto> clearAll(Authentication authentication) {
        historyService.clearAll(AuthenticationUser.get(authentication).getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> clearAll(Authentication authentication, @PathVariable long id) {
        historyService.clearById(AuthenticationUser.get(authentication), id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
