package com.websystem.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystem.entity.db.NoteEntity;

@Repository
public interface noteRepository extends JpaRepository<NoteEntity, String> {
  public NoteEntity findByNoteIdIs(String noteId);
}
