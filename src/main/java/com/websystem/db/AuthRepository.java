package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.AuthEntity;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String> {
    public AuthEntity findByUserIdIs(String password);
}
