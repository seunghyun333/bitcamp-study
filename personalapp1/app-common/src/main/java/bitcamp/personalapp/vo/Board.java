package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Board implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private Member mno;
  private String title;
  private String content;
  private Timestamp w_date;
  private int v_count;
  private boolean secret;


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
    Board other = (Board) obj;
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

  public Timestamp getW_date() {
    return w_date;
  }

  public void setW_date(Timestamp w_date) {
    this.w_date = w_date;
  }

  public int getV_count() {
    return v_count;
  }

  public void setV_count(int v_count) {
    this.v_count = v_count;
  }

  public boolean isSecret() {
    return secret;
  }

  public void setSecret(boolean secret) {
    this.secret = secret;
  }


}
