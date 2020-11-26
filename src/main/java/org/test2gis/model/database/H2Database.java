package org.test2gis.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class H2Database {
  @Value("${booking.db.url}")
  private String url;

  private Connection connection;

  @SneakyThrows
  public H2Database() {}

  @PostConstruct
  @SneakyThrows
  public void createSchema(){
    connection = DriverManager.getConnection(url);

    this.execute("CREATE TABLE SEAT (ID INT PRIMARY KEY, SEAT_NUMBER VARCHAR(24), "
        + "IS_BOOKING BOOLEAN, ROW_ID INT, HALL_ID INT, CINEMA_ID INT)");

    this.execute("INSERT INTO SEAT VALUES (1, 'A1', false, 1, 1, 1), (2, 'A2', false, 1, 1, 1)");
    this.execute("COMMIT");
  }

  @SneakyThrows
  public void execute(String command) {
    Statement statement = connection.createStatement();
    statement.execute(command);
    statement.close();
  }

  @SneakyThrows
  public List<Map<String, Object>> getData(String select, List<SqlColProfile> sqlColMetadataList) {
    PreparedStatement ps = connection.prepareStatement(select);
    ResultSet rs = ps.executeQuery();

    List<Map<String, Object>> rows = new ArrayList<>();

    while (rs.next()) {
      Map<String, Object> columns = new LinkedHashMap<>();

      for (int i = 0; i < sqlColMetadataList.size(); i++) {
        columns.put(sqlColMetadataList.get(i).getColName(), rs.getObject(i+1));
      }

      rows.add(columns);
    }

    rs.close();
    ps.close();

    return rows;
  }

  @SneakyThrows
  public List<SqlColProfile> loadSqlColMetadataList(String select) {
    List<SqlColProfile> sqlColMetadataList = new ArrayList<>();

    Statement s;
    ResultSet rs;
    ResultSetMetaData rsmd;

    s = connection.createStatement();
    s.executeQuery(select);
    rs = s.getResultSet();
    rsmd = rs.getMetaData();

    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
      sqlColMetadataList.add(i - 1,
      SqlColProfile.builder()
          .colId(i-1)
          .colIdSql(i)
          .colName(rsmd.getColumnName(i).toUpperCase())
          .colDbTypeName(rsmd.getColumnTypeName(i).toUpperCase())
          .colSizeDisplay(rsmd.getColumnDisplaySize(i))
          .colSizeSqlType(rsmd.getColumnType(i))
          .build());
    }

    rs.close();
    s.close();

    return sqlColMetadataList;
  }

  @SneakyThrows
  public void close(){
    connection.close();
  }
}
