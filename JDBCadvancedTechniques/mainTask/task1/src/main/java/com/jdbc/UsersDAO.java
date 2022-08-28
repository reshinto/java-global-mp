package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.util.DataAccessObject;

public class UsersDAO extends DataAccessObject<Users> {
  private static final String INSERT = "INSERT INTO users (name, surname, date_of_birth) VALUES (?, ?, ?)";

  private static final String GET_USERS_WITH_CONDITIONS = "SELECT name, surname FROM users inner join get_users_friends(?) f ON f.user_id=users.user_id inner join get_users_likes(?, ?) l ON l.user_id=users.user_id";

  public UsersDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Users findById(long id) {
    return null;
  }

  @Override
  public List<Users> findAll() {
    return null;
  }

  @Override
  public Users update(Users dto) {
    return null;
  }

  @Override
  public Users create(Users dto) {
    try {
      PreparedStatement statement = this.connection.prepareStatement(INSERT);
      statement.setString(1, dto.getName());
      statement.setString(2, dto.getSurname());
      statement.setDate(3, dto.getDateOfBirth());
      statement.execute();
      return null;
    }catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public List<UserLine> getUsersForConditions(int friends, Timestamp timestamp, int likes) {
    List<UserLine> userLines = new ArrayList<>();
    try {
      PreparedStatement statement = this.connection.prepareStatement(GET_USERS_WITH_CONDITIONS);
      statement.setInt(1, friends);
      statement.setTimestamp(2, timestamp);
      statement.setInt(3, likes);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()){
        UserLine userLine = new UserLine();
        userLine.setName(resultSet.getString(1));
        userLine.setSurname(resultSet.getString(2));
        userLines.add(userLine);
      }
    }catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    return userLines;
  }

  @Override
  public void delete(long id) {}
}
