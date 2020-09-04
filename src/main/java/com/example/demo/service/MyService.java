package com.example.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.exception.MyException;

@Service
public class MyService {

  private Logger logger = LoggerFactory.getLogger(MyService.class);

  @Value("${USER:system}")
  public String user;

  @Value("${PWD:oracle}")
  public String password;

  @Value("${HOST:localhost}")
  public String host;

  @Value("${PORT:49161}")
  public int port;

  @Value("${SID:xe}")
  public String sid;

  private Connection conn = null;

  @PostConstruct
  public Connection initConnection() {

    String url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
    try {
      conn = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("Error: ", e);
    }
    return conn;
  }

  public Object executSQL(String sql) {
    Statement ps = null;
    try {
      ps = conn.createStatement();
      ResultSet rs = null;
      if (sql.toLowerCase().contains("select")) {
        rs = ps.executeQuery(sql);
      } else {
        return ps.executeUpdate(sql);
      }

      return convertList(rs);
    } catch (Exception e) {
      throw new MyException(e);
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
        logger.error("error:",e);
      }
    }
  }

  private List<Map> convertList(ResultSet rs) throws SQLException {
    List<Map> list = new ArrayList<>();
    ResultSetMetaData md = rs.getMetaData();
    int columnCount = md.getColumnCount();
    while (rs.next()) {
      Map<String, Object> rowData = new HashMap<>();
      for (int i = 1; i <= columnCount; i++) {
        rowData.put(md.getColumnName(i), rs.getObject(i));
      }
      list.add(rowData);
    }
    return list;

  }
}
