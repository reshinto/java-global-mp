package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.util.DataAccessObject;

public class PostsDAO extends DataAccessObject<Posts> {
  private static final String INSERT = "INSERT INTO posts (user_id, text, created_datetime, updated_datetime) VALUES (?, ?, ?, ?)";

  public PostsDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Posts findById(long id) {
    return null;
  }

  @Override
  public List<Posts> findAll() {
    return null;
  }

  @Override
  public Posts update(Posts dto) {
    return null;
  }

  @Override
  public Posts create(Posts dto) {
    try {
      PreparedStatement statement = this.connection.prepareStatement(INSERT);
      statement.setLong(1, dto.getUserId());
      statement.setString(2, dto.getText());
      statement.setTimestamp(3, dto.getCreatedDatetime());
      statement.setTimestamp(4, dto.getUpdatedDatetime());
      statement.execute();
      return null;
    }catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(long id) {}
}
