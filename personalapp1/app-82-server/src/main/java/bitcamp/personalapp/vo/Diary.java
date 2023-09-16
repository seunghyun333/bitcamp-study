package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Diary implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private Board cno;
  private String weather;



  @Override
  public String toString() {
    return "Diray [no=" + no + ", cno=" + cno + ", weather=" + weather + "]";
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
    Diary other = (Diary) obj;
    return no == other.no;
  }

public int getNo() {
	return no;
}

public void setNo(int no) {
	this.no = no;
}

public Board getCno() {
	return cno;
}

public void setCno(Board cno) {
	this.cno = cno;
}

public String getWeather() {
	return weather;
}

public void setWeather(String weather) {
	this.weather = weather;
}


  

}
