package io.xgeekshq.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.xgeekshq.demo.domain.User;

// by extending JpaRepository, this is recognized automatically as a bean
// If no interface is extended, I can use @Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContaining(String keyword);

}