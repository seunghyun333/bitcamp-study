package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Visit implements Serializable {
  private static final long serialVersionUID = 1L;


  private int no;
  private String name;
  private Timestamp createdDate;

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


    Visit other = (Visit) obj;
    return no == other.no;

  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }


}


