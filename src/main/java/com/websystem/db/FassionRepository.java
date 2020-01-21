package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.FassionEntity;

/**
 * 服情報DBアクセス用
 */
@Repository
public interface FassionRepository extends JpaRepository<FassionEntity, String> {

}
