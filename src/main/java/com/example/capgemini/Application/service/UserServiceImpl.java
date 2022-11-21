package com.example.capgemini.Application.service;

import com.example.capgemini.Application.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

}
