package com.epam.grow.service.impl;

import com.epam.grow.domain.UserEntity;
import com.epam.grow.repository.UserRepository;
import com.epam.grow.service.api.RegistrationService;
import com.epam.grow.service.api.dto.Role;
import com.epam.grow.service.api.dto.User;
import com.epam.grow.service.api.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder encoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new RuntimeException("User with this username already exists");
        });
        if (user.getAuthorities().isEmpty()) {
            user.getAuthorities().add(Role.USER);
        }
        UserEntity userEntity = userMapper.convertDtoToEntity(user);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        return userMapper.convertEntityToDto(userRepository.save(userEntity));
    }
}
