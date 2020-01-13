package com.websystem.entity.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class NoteEntity {

  @Id
  @Column(name = "note_id")
  private String noteId;

  @Column(name = "contributor_id")
  private String contributorId;

  @Column(name = "title_name")
  private String title;

  @Column(name = "body")
  private String body;

  @Column(name = "fassion_id_list")
  private String fassionIdList;

  @Column(name = "created_day")
  private Date createdDate;

  public String getNoteId() {
    return noteId;
  }

  public void setNoteId(String noteId) {
    this.noteId = noteId;
  }

  public String getContributorId() {
    return contributorId;
  }

  public void setContributorId(String contributorId) {
    this.contributorId = contributorId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getFassionIdList() {
    return fassionIdList;
  }

  public void setFassionIdList(String fassionIdList) {
    this.fassionIdList = fassionIdList;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
