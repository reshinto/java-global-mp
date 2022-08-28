package com.jdbc;

import java.sql.Timestamp;

import com.jdbc.util.DataTransferObject;

public class Friendships implements DataTransferObject {
  private long id;
  private long firstUserId;
  private long secondUserId;
  private Timestamp createdDatetime;
  private Timestamp updatedDatetime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getFirstUserId() {
    return firstUserId;
  }

  public void setFirstUserId(long id) {
    this.firstUserId = id;
  }

  public long getSecondUserId() {
    return secondUserId;
  }

  public void setSecondUserId(long id) {
    this.secondUserId = id;
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
    final StringBuilder sb = new StringBuilder("Friendships{");
    sb.append("id=").append(id);
    sb.append(", firstUserId='").append(firstUserId).append('\'');
    sb.append(", secondUserId='").append(secondUserId).append('\'');
    sb.append(", createdDatetime='").append(createdDatetime).append('\'');
    sb.append(", updatedDatetime='").append(updatedDatetime).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
