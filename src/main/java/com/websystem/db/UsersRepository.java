package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.UsersEntity;

/**
 * ユーザ情報DBアクセス用
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
  /**
   * ユーザIDからユーザ情報を検索する
   * @param userId 検索するユーザID
   * @return
   */
  public UsersEntity findByUserIdIs(String userId);
}
