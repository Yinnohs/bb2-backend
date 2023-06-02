package com.yinnohs.bb2.Example.application.repository;

import com.yinnohs.bb2.Example.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
