package com.zaxxer.hikari;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Demo0 {

   @Test
   public void test02() throws SQLException {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
      config.setUsername("root");
      config.setPassword("root");
      config.setDriverClassName("com.mysql.jdbc.Driver");
      config.setMaximumPoolSize(10);
      config.setMinimumIdle(5);
      config.setConnectionTimeout(5000);
      config.setIdleTimeout(600000);
      config.setMaxLifetime(1800000);

      HikariDataSource dataSource = new HikariDataSource(config);
      List<Connection> list = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
         Connection conn = dataSource.getConnection();
         list.add(conn);
      }
      for (Connection conn : list) {
         conn.close();
      }

      System.out.println();
   }

   @Test
   public void test() throws Exception {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
      config.setUsername("root");
      config.setPassword("root");
      config.setDriverClassName("com.mysql.jdbc.Driver");
      config.setMaximumPoolSize(10);
      config.setMinimumIdle(5);
      config.setConnectionTimeout(5000);
      config.setIdleTimeout(600000);
      config.setMaxLifetime(1800000);

      HikariDataSource dataSource = new HikariDataSource(config);

      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      statement.execute("select * from user");
      ResultSet resultSet = statement.getResultSet();
      while (resultSet.next()) {
         int userId = resultSet.getInt("user_id");
         String userName = resultSet.getString("user_name");
         System.out.println("userId: " + userId + " userName: " + userName);
      }

   }
}
