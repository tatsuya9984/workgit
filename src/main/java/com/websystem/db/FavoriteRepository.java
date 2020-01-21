package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.FavoriteEntity;

/**
 * ファボ情報DBアクセス用
 */
@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, String> {

}
