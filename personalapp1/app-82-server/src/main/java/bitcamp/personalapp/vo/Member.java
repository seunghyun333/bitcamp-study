package bitcamp.personalapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;


  private int no;
  private String name;
  private String email;
  private String pw;
  private String tel;
  private Timestamp w_date;
  private String photo;

  

  @Override
public String toString() {
	return "Member [no=" + no + ", name=" + name + ", email=" + email + ", pw=" + pw + ", tel=" + tel + ", w_date="
			+ w_date + ", photo=" + photo + "]";
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


    Member other = (Member) obj;
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


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getPw() {
    return pw;
  }


  public void setPw(String pw) {
    this.pw = pw;
  }


  public String getTel() {
    return tel;
  }


  public void setTel(String tel) {
    this.tel = tel;
  }


  public Timestamp getW_date() {
    return w_date;
  }


  public void setW_date(Timestamp w_date) {
    this.w_date = w_date;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public String getPhoto() {
    return photo;
  }


  public void setPhoto(String photo) {
    this.photo = photo;
  }



}


