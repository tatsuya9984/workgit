package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
  public UsersEntity findByUserIdIs(String userId);
}
