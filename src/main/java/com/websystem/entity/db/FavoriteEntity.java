package com.websystem.entity.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorite", schema = "public")
public class FavoriteEntity {

  @Id
  @Column(name = "note_id")
  private String noteId;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "favorite_day")
  private Date favoriteDay;

  public String getNoteId() {
    return noteId;
  }

  public void setNoteId(String noteId) {
    this.noteId = noteId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getFavoriteDay() {
    return favoriteDay;
  }

  public void setFavoriteDay(Date favoriteDay) {
    this.favoriteDay = favoriteDay;
  }
}
