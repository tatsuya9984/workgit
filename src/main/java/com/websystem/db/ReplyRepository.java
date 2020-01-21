package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.ReplyEntity;

/**
 * 返信情報DBアクセス用
 */
@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, String> {

}
