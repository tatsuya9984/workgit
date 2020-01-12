package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  public UserEntity findByUserIdIs(String userId);
}
