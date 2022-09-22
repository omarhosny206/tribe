package com.example.controller;

import com.example.dto.TribeDto;
import com.example.service.TribeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("tribe/")
public class TribeController {
    private final TribeService tribeService;

    public TribeController(TribeService tribeService) {
        this.tribeService = tribeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(Principal principal, @RequestBody TribeDto tribeDto) {
        System.out.println(tribeDto.getName());
        return tribeService.create(principal, tribeDto);
    }
}
