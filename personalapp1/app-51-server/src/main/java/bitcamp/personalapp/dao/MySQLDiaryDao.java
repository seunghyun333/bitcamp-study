package bitcamp.personalapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.DataSource;

public class MySQLDiaryDao implements DiaryDao{
	
	DataSource ds;
	
	public MySQLDiaryDao(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void insert(Diary diary) {
		try(PreparedStatement stmt = ds.getConnection(false).prepareStatement(
				"insert into personalapp_diary(writedate,title,weather,content,coffee)"
				+ " values(?,?,?,?,?)")){
			
			stmt.setString(1, diary.getDate());
			stmt.setString(2, diary.getTitle());
			stmt.setString(3, diary.getWeather());
			stmt.setString(4, diary.getContents());
			stmt.setString(5, String.valueOf(diary.getCoffee()));
			
			stmt.executeUpdate();
			
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			}

	@Override
	public List<Diary> list() {
		try(PreparedStatement stmt = ds.getConnection(false).prepareStatement(
				"select diary_no, writedate, title, weather, content, coffee"
				+ " from personalapp_diary"
				+ " order by diary_no");
				
			ResultSet rs = stmt.executeQuery()) {
			
			List<Diary> list = new ArrayList<>();
			
			while (rs.next()) {
				Diary d = new Diary();
				d.setNo(rs.getInt("diary_no"));
				d.setDate(rs.getString("writedate"));
				d.setTitle(rs.getString("title"));
				d.setWeather(rs.getString("weather"));
				d.setContents(rs.getString("content"));
				d.setCoffee(rs.getString("coffee").charAt(0));
				
				list.add(d);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Diary findBy(int no) {
		try(PreparedStatement stmt = ds.getConnection(false).prepareStatement(
				"select diary_no, writedate, title, weather, content, coffee "
				+ "from personalapp_diary "
				+ "where diary_no=?")) {
			
			stmt.setInt(1, no);
				
			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Diary d = new Diary();
					d.setNo(rs.getInt("diary_no"));
					d.setDate(rs.getString("writedate"));
					d.setTitle(rs.getString("title"));
					d.setWeather(rs.getString("weather"));
					d.setContents(rs.getString("content"));
					d.setCoffee(rs.getString("coffee").charAt(0));
					
					return d;
				}
				return null;
			}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public int update(Diary diary) {
		try(PreparedStatement stmt = ds.getConnection(false).prepareStatement(
				"update personalapp_diary set"
				+ " writedate=?,"
				+ " title=?,"
				+ " weather=?,"
				+ " content=?,"
				+ " coffee=?"
				+ " where diary_no=?")){
			
			stmt.setString(1, diary.getDate());
			stmt.setString(2, diary.getTitle());
			stmt.setString(3, diary.getWeather());
			stmt.setString(4, diary.getContents());
			stmt.setString(5, String.valueOf(diary.getCoffee()));
			stmt.setInt(6, diary.getNo());
			
			return stmt.executeUpdate();
			
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

	@Override
	public int delete(int no) {
		try(PreparedStatement stmt = ds.getConnection(false).prepareStatement(
				"delete from personalapp_diary "
				+ "where diary_no=?")) {
			stmt.setInt(1, no);
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
