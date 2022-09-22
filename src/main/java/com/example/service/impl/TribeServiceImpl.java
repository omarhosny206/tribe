package com.example.service.impl;

import com.example.dto.TribeDto;
import com.example.entity.Tribe;
import com.example.entity.User;
import com.example.repository.TribeRepository;
import com.example.repository.UserRepository;
import com.example.response.MessageResponse;
import com.example.service.TribeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
public class TribeServiceImpl implements TribeService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final TribeRepository tribeRepository;

    public TribeServiceImpl(ModelMapper modelMapper, UserRepository userRepository, TribeRepository tribeRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tribeRepository = tribeRepository;
    }

    @Override
    public ResponseEntity<?> create(Principal principal, TribeDto tribeDto) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        Tribe tribe = modelMapper.map(tribeDto, Tribe.class);
        tribe.getUsers().add(user);
        tribeRepository.save(tribe);
        return new ResponseEntity<>(new MessageResponse("tribe created successfully"), HttpStatus.OK);
    }
}
