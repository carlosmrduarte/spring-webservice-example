package io.xgeekshq.demo.repository;

import io.xgeekshq.demo.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// by extending JpaRepository, this is recognized automatically as a bean
// If no interface is extended, I can use @Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // method query
    List<User> findByEmailContaining(String keyword);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email LIKE %:keyword%")
    List<User> findByEmailWithKeyword(@Param("keyword") String keyword);
}
