package com.tribe.controller;

import com.tribe.dto.MessageDto;
import com.tribe.dto.UserTribeDto;
import com.tribe.entity.UserTribe;
import com.tribe.service.UserTribeService;
import com.tribe.util.AuthenticationUser;
import com.tribe.util.UserTribeId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-tribes")
public class UserTribeController {
    private final UserTribeService userTribeService;

    public UserTribeController(UserTribeService userTribeService) {
        this.userTribeService = userTribeService;
    }


    @GetMapping("/")
    public ResponseEntity<List<UserTribe>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userTribeService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<List<UserTribe>> getAllByUserId(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userTribeService.getAllByUserId(AuthenticationUser.get(authentication).getId()));
    }

    @PostMapping("/")
    public ResponseEntity<UserTribe> save(Authentication authentication, @Valid @RequestBody UserTribeDto userTribeDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userTribeService.save(AuthenticationUser.get(authentication), userTribeDto));
    }

    @DeleteMapping("/{tribeId}")
    public ResponseEntity<MessageDto> deleteById(Authentication authentication, @PathVariable long tribeId) {
        userTribeService.deleteById(new UserTribeId(AuthenticationUser.get(authentication).getId(), tribeId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageDto("Success"));
    }
}
