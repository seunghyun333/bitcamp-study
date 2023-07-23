package bitcamp.personalapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bitcamp.personalapp.vo.Board;

public class MySQLBoardDao implements BoardDao{
	
	Connection con;
	
	public MySQLBoardDao(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Board board) {
		try(Statement stmt = con.createStatement()) {
			
			stmt.executeUpdate(String.format("insert into personalapp_board(title,content,writer,password) values('%s','%s','%s','%s')",
					board.getTitle(),
					board.getContent(),
					board.getWriter(),
					board.getPassword()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Board> list() {
		try(Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select Board_no, title, content, writer, password from personalapp_board order by board_no asc")){
			
			List<Board> list = new ArrayList<>();
			
			while (rs.next()) {
				Board b = new Board();
				b.setNo(rs.getInt("board_no"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setWriter(rs.getString("writer"));
				b.setPassword(rs.getString("password"));
				
				list.add(b);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Board findBy(int no) {
		try(Statement stme=con.createStatement();
				ResultSet rs = stme.executeQuery(
						"select Board_no, title, content, writer, password from personalapp_board where board_no=" +no)){

				while (rs.next()) {
					Board b = new Board();
					b.setNo(rs.getInt("board_no"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("content"));
					b.setWriter(rs.getString("writer"));
					b.setPassword(rs.getString("password"));
					
					return b;
				}
				return null;
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public int update(Board board) {
		try(Statement stmt = con.createStatement()) {
			
			return stmt.executeUpdate(String.format(
					"update personalapp_board set"
					+ " title= '%s', "
					+ " content= '%s', "
					+ " writer= '%s', "
					+ " password= '%s', +"
					+ " where board_no=%d",
					board.getTitle(),
					board.getContent(),
					board.getWriter(),
					board.getPassword(),
					board.getNo()));
					
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int delete(int no) {
		try(Statement stmt = con.createStatement()){
			return stmt.executeUpdate(String.format(
					"delete form personalapp_boardr where board_no=%d",
					no));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
