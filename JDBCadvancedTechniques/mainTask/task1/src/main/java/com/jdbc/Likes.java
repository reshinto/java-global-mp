package com.jdbc;

import java.sql.Timestamp;

import com.jdbc.util.DataTransferObject;

public class Likes implements DataTransferObject {
  private long id;
  private long postId;
  private long userId;
  private Timestamp createdDatetime;
  private Timestamp updatedDatetime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPostId() {
    return postId;
  }

  public void setPostId(long id) {
    this.postId = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long id) {
    this.userId = id;
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
    final StringBuilder sb = new StringBuilder("Likes{");
    sb.append("id=").append(id);
    sb.append(", postId='").append(postId).append('\'');
    sb.append(", userId='").append(userId).append('\'');
    sb.append(", createdDatetime='").append(createdDatetime).append('\'');
    sb.append(", updatedDatetime='").append(updatedDatetime).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
