package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.AuthEntity;

/**
 * 認証情報DBアクセス用
 */
@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String> {
  /**
   * ユーザIDに紐づく認証情報の検索
   * @param userId 検索するユーザID
   * @return
   */
  public AuthEntity findByUserIdIs(String userId);

  /**
   * LINE_IDに紐づく認証情報の検索
   * @param lineId 検索するLINE_ID
   * @return
   */
  public AuthEntity findByLineIdIs(String lineId);
}
