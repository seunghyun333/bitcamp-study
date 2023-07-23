package bitcamp.personalapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.personalapp.vo.Visit;

public class MySQLVisitDao implements VisitDao {

  Connection con;

  public MySQLVisitDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Visit visit) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(
          String.format("insert into personalapp_visit(name) values('%s')", visit.getName()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Visit> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select visit_no, name from personalapp_visit order by visit_no")) {

      List<Visit> list = new ArrayList<>();

      while (rs.next()) {
        Visit v = new Visit();
        v.setNo(rs.getInt("visit_no"));
        v.setName(rs.getString("name"));

        list.add(v);
      }
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
