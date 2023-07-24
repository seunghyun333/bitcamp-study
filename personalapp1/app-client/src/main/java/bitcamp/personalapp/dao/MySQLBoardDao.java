package bitcamp.personalapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.personalapp.vo.Board;
import bitcamp.personalapp.vo.Visit;

public class MySQLBoardDao implements BoardDao {

  Connection con;

  public MySQLBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into personalapp_board(title,content,writer,password)"
            + " values(?,?,?,sha1(?))")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());
      stmt.setString(4, board.getPassword());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Board> list() {
    try (PreparedStatement stmt = con.prepareStatement("select" + " b.Board_no," + " b.title, "
        + " b.view_count, " + " b.created_date, " + " v.visit_No, " + " v.name" + " from"
        + " personalapp_board b inner join personalapp_visit v on b.writer=v.visit_no"
        + " order by board_no desc"); ResultSet rs = stmt.executeQuery()) {

      List<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_no"));
        b.setTitle(rs.getString("title"));
        b.setViewCount(rs.getInt("view_count"));
        b.setCreatedDate(rs.getTimestamp("created_date"));

        Visit writer = new Visit();
        writer.setNo(rs.getInt("visit_no"));
        writer.setName(rs.getString("name"));
        b.setWriter(writer);

        list.add(b);
      }
      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement("select" + " b.Board_no," + " b.title, "
        + " b.view_count, " + " b.created_date, " + " v.visit_No, " + " v.name" + " from"
        + " personalapp_board b inner join personalapp_visit v on b.writer=v.visit_no"
        + " order by board_no desc")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          Visit writer = new Visit();
          writer.setNo(rs.getInt("visit_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);

          stmt.executeUpdate("update personalapp_board set" + " view_count=view_count + 1"
              + " where board_no=" + no);
          return b;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = con.prepareStatement("update personalapp_board set" + " title= ?,"
        + " content= ?" + " where board_no=? and password=sha1(?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getNo());
      stmt.setString(4, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from personalapp_board" + " where board_no=? and password=sha1(?)")) {

      stmt.setInt(1, board.getNo());
      stmt.setString(2, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



}
