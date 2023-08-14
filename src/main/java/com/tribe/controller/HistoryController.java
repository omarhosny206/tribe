package com.tribe.controller;

import com.tribe.dto.MessageDto;
import com.tribe.entity.History;
import com.tribe.service.HistoryService;
import com.tribe.util.AuthenticationUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
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
