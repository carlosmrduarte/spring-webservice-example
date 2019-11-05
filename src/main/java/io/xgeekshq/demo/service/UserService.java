package io.xgeekshq.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.xgeekshq.demo.domain.User;
import io.xgeekshq.demo.dto.UserDto;
import io.xgeekshq.demo.mapper.UserMapper;
import io.xgeekshq.demo.repository.UserRepository;

/**
 * UserService
 */
@Service
@Transactional
public class UserService {

    private UserRepository repository;
    private UserMapper mapper;

    // Repository is injected by Spring
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return this.repository.findById(id).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return this.repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Long saveUser(UserDto user) {
        User newUser = this.repository.save(mapper.toEntity(user));
        return newUser.getId();
    }

    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }

}