package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Board implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String title; 
	private String content;
	private Visit writer;
	private String password;
	private int viewCount;
	private Timestamp createdDate;
	
	
  @Override
  public int hashCode() {
	  return Objects.hash(no);
  }
	
	public boolean equals (Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Board board =(Board) obj;
		if(this.getNo() != board.getNo()) {
			return false;
		}
		return true;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Visit getWriter() {
		return writer;
	}

	public void setWriter(Visit writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}



	
	
}