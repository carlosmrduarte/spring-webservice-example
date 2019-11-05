package io.xgeekshq.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.xgeekshq.demo.dto.UserDto;
import io.xgeekshq.demo.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private UserService service;

    // UserService is injected by Spring
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> user = this.service.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return this.service.findAll();
    }

    @GetMapping("/users-by-email-keyword")
    public List<UserDto> getUsersByEmailKeyword(@RequestParam String keyword) {
        return this.service.findUsersByEmailKeyword(keyword);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        this.service.deleteUser(id);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserDto user) {
        Long newUserId = this.service.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}").buildAndExpand(newUserId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}