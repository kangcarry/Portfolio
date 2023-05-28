package com.itwill.ilhajob.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    //Optional<User> findAppListById(Long id);
}
