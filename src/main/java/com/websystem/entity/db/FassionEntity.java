package com.websystem.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fassion")
public class FassionEntity {

  @Id
  @Column(name = "fassion_id")
  private String fassionId;

  @Column(name = "note_id")
  private String noteId;

  @Column(name = "source")
  private String source;

  @Column(name = "alt_message")
  private String altMessage;

  @Column(name = "season")
  private String season;

  @Column(name = "month")
  private int month;

  @Column(name = "companey")
  private String companey;

  @Column(name = "url")
  private String url;

  public String getFassionId() {
    return fassionId;
  }

  public void setFassionId(String fassionId) {
    this.fassionId = fassionId;
  }

  public String getNoteId() {
    return noteId;
  }

  public void setNoteId(String noteId) {
    this.noteId = noteId;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getAltMessage() {
    return altMessage;
  }

  public void setAltMessage(String altMessage) {
    this.altMessage = altMessage;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public String getCompaney() {
    return companey;
  }

  public void setCompaney(String companey) {
    this.companey = companey;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
