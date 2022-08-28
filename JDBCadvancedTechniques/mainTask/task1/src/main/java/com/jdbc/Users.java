package com.jdbc;

import java.sql.Date;

import com.jdbc.util.DataTransferObject;

public class Users implements DataTransferObject {
  private long id;
  private String name;
  private String surname;
  private Date dateOfBirth;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Users{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", surname='").append(surname).append('\'');
    sb.append(", dateOfBirth='").append(dateOfBirth).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
