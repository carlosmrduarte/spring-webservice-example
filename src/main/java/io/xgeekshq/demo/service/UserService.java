package io.xgeekshq.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.xgeekshq.demo.domain.User;
import io.xgeekshq.demo.dto.UserDto;
import io.xgeekshq.demo.mapper.UserMapper;
import io.xgeekshq.demo.repository.UserRepository;

@Service // mark as component/bean, for the service layer
@Transactional // default annotation for all public methods
public class UserService {

    private UserRepository repository;

    private UserMapper mapper;

    // Constructor arguments are injected by Spring
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true) // override default annotation
    public List<UserDto> findAll() {
        return this.mapper.toDto(this.repository.findAll());
    }

    public Long saveUser(UserDto user) {
        User newUser = this.repository.save(mapper.toEntity(user));
        return newUser.getId();
    }

    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }

    @Transactional(readOnly = true) // override default annotation
    public Optional<UserDto> findById(Long id) {
        return this.repository.findById(id).map(mapper::toDto);
    }

    public List<UserDto> findUsersByEmailKeyword(String keyword) {
        return this.mapper.toDto(this.repository.findByEmailWithKeyword(keyword));
    }

}