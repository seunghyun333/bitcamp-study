package bitcamp.personalapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.personalapp.vo.Visit;
import bitcamp.util.DataSource;

public class MySQLVisitDao implements VisitDao {

  DataSource ds;

  public MySQLVisitDao(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public void insert(Visit visit) {
    try (PreparedStatement stmt =
    		ds.getConnection(false).prepareStatement("insert into personalapp_visit(name) values(?)")) {

      stmt.setString(1, visit.getName());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Visit> list() {
    try (
        PreparedStatement stmt = ds.getConnection(false).prepareStatement(
            "select visit_no, name, created_date" + " from personalapp_visit order by visit_no");
        ResultSet rs = stmt.executeQuery()) {

      List<Visit> list = new ArrayList<>();

      while (rs.next()) {
        Visit v = new Visit();
        v.setNo(rs.getInt("visit_no"));
        v.setName(rs.getString("name"));
        v.setCreatedDate(rs.getTimestamp("created_date"));

        list.add(v);
      }
      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Visit findByIdAndPassword(Visit param) {
    try (PreparedStatement stmt =
    		ds.getConnection(false).prepareStatement("select visit_no from personalapp_visit where name=?")) {

      stmt.setString(1, param.getName());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Visit v = new Visit();
          v.setNo(rs.getInt("visit_no"));
          return v;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
