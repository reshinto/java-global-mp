package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.util.DataAccessObject;

public class FriendshipsDAO extends DataAccessObject<Friendships> {
  private static final String INSERT = "INSERT INTO friendships (first_user_id, second_user_id) VALUES (?, ?)";

  public FriendshipsDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Friendships findById(long id) {
    return null;
  }

  @Override
  public List<Friendships> findAll() {
    return null;
  }

  @Override
  public Friendships update(Friendships dto) {
    return null;
  }

  @Override
  public Friendships create(Friendships dto) {
    try {
      PreparedStatement statement = this.connection.prepareStatement(INSERT);
      statement.setLong(1, dto.getFirstUserId());
      statement.setLong(2, dto.getSecondUserId());
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
