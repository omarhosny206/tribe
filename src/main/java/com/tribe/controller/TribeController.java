package com.tribe.controller;

import com.tribe.dto.MessageDto;
import com.tribe.dto.TribeRequestDto;
import com.tribe.entity.Tribe;
import com.tribe.service.TribeService;
import com.tribe.util.AuthenticationUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tribes")
public class TribeController {
    private final TribeService tribeService;

    public TribeController(TribeService tribeService) {
        this.tribeService = tribeService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Tribe>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tribeService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<Tribe>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tribeService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tribe> getById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tribeService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Tribe> getByName(@PathVariable String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tribeService.getByName(name));
    }

    @PostMapping("/")
    public ResponseEntity<Tribe> save(Authentication authentication, @Valid @RequestBody TribeRequestDto tribeRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tribeService.save(AuthenticationUser.get(authentication), tribeRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tribe> update(Authentication authentication, @PathVariable long id, @Valid @RequestBody TribeRequestDto tribeRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tribeService.update(AuthenticationUser.get(authentication), id, tribeRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteById(Authentication authentication, @PathVariable long id) {
        tribeService.deleteById(AuthenticationUser.get(authentication), id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
