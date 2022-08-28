package com.jdbc;

import java.sql.Timestamp;

import com.jdbc.util.DataTransferObject;

public class Posts implements DataTransferObject {
  private long id;
  private long userId;
  private String text;
  private Timestamp createdDatetime;
  private Timestamp updatedDatetime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long id) {
    this.userId = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getCreatedDatetime() {
    return createdDatetime;
  }

  public void setCreatedDatetime(Timestamp datetime) {
    this.createdDatetime = datetime;
  }

  public Timestamp getUpdatedDatetime() {
    return updatedDatetime;
  }

  public void setUpdatedDatetime(Timestamp datetime) {
    this.updatedDatetime = datetime;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Posts{");
    sb.append("id=").append(id);
    sb.append(", userId='").append(userId).append('\'');
    sb.append(", text='").append(text).append('\'');
    sb.append(", createdDatetime='").append(createdDatetime).append('\'');
    sb.append(", updatedDatetime='").append(updatedDatetime).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
