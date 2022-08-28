package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.util.DataAccessObject;

public class LikesDAO extends DataAccessObject<Likes> {
  private static final String INSERT = "INSERT INTO likes (post_id, user_id, created_datetime, updated_datetime) VALUES (?, ?, ?, ?)";

  public LikesDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Likes findById(long id) {
    return null;
  }

  @Override
  public List<Likes> findAll() {
    return null;
  }

  @Override
  public Likes update(Likes dto) {
    return null;
  }

  @Override
  public Likes create(Likes dto) {
    try {
      PreparedStatement statement = this.connection.prepareStatement(INSERT);
      statement.setLong(1, dto.getPostId());
      statement.setLong(2, dto.getUserId());
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
