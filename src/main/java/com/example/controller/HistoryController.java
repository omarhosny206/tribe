package com.example.controller;

import com.example.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @DeleteMapping("/")
    public ResponseEntity<?> clear(Principal principal) {
        return historyService.clear(principal);
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getAll(Principal principal) {
        return historyService.getAll(principal);
    }
}
