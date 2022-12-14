package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.HistoryRepository;
import com.example.repository.UserRepository;
import com.example.response.MessageResponse;
import com.example.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(UserRepository userRepository, HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> clear(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        historyRepository.deleteByUserId(user.getId());
        return new ResponseEntity<>(new MessageResponse("history cleared"), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<String>> getAll(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return new ResponseEntity<>(historyRepository.getByUserId(user.getId()), HttpStatus.OK);
    }

}
