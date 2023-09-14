package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Objects;

public class Comment implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private Member mno;
  private Board cno;
  private String content;
  private Timestamp w_date;




  @Override
public String toString() {
	return "Comment [no=" + no + ", mno=" + mno + ", cno=" + cno + ", content=" + content + ", w_date=" + w_date + "]";
}

@Override
  public int hashCode() {
    return Objects.hash(no);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (this.getClass() != obj.getClass())
      return false;
    Comment other = (Comment) obj;
    return no == other.no;
  }

public int getNo() {
	return no;
}

public void setNo(int no) {
	this.no = no;
}

public Member getMno() {
	return mno;
}

public void setMno(Member mno) {
	this.mno = mno;
}

public Board getCno() {
	return cno;
}

public void setCno(Board cno) {
	this.cno = cno;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public Timestamp getW_date() {
	return w_date;
}

public void setW_date(Timestamp w_date) {
	this.w_date = w_date;
}

  

}
