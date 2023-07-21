package bitcamp.personalapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bitcamp.personalapp.vo.Diary;

public class MySQLDiaryDao implements DiaryDao{
	
	Connection con;
	
	public MySQLDiaryDao(Connection con) {
		this.con = con;
		}
	


	@Override
	public void insert(Diary diary) {
		try(Statement stmt = con.createStatement()){
			
			stmt.executeUpdate(String.format(
					"insert into personalapp_diary(writedate,title,weather,content,coffee) values('%s','%s','%s','%s','%c')",
					diary.getDate(),
					diary.getTitle(),
					diary.getWeather(),
					diary.getContents(),
					diary.getCoffee()));
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

	@Override
	public List<Diary> list() {
		try(Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select diary_no, writedate, title, weather, content, coffee from personalapp_diary order by diary_no")) {
			
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
		try(Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select diary_no, writedate, title, weather, content, coffee from personalapp_diary where diary_no=" + no)) {
				
				
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

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public int update(Diary diary) {
		try(Statement stmt = con.createStatement()){
			
			return stmt.executeUpdate(String.format(
					"update personalapp_diary set"
					+ " writedate='%s',"
					+ " title='%s'"
					+ " weather='%s'"
					+ " content='%s'"
					+ " coffee='%c'"
					+ " where diary_no=%d",
					diary.getDate(),
					diary.getTitle(),
					diary.getWeather(),
					diary.getContents(),
					diary.getCoffee(),
					diary.getNo()));
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

	@Override
	public int delete(int no) {
		try(Statement stmt = con.createStatement()) {
			return stmt.executeUpdate(String.format(
					"delete from personalapp_diary where diary_no=%d",
					no));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
