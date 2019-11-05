package io.xgeekshq.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.xgeekshq.demo.domain.User;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmailContaining(String keyword);

}