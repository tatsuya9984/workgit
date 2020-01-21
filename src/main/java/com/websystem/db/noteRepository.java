package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.NoteEntity;

/**
 * 投稿情報DBアクセス用
 */
@Repository
public interface noteRepository extends JpaRepository<NoteEntity, String> {
  public NoteEntity findByNoteIdIs(String noteId);
}
