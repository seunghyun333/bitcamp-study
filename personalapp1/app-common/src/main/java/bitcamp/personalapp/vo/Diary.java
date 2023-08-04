package bitcamp.personalapp.vo;

import java.io.Serializable;

public class Diary implements Serializable {
  private static final long serialVersionUID = 1L;


  public static final char DRINK = 'O';
  public static final char NONCOFFEE = 'X';

  public int no;
  public String date;
  public String title;
  public String weather;
  public String content;
  private String password;
  public char coffee;


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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getCoffee() {
    return coffee;
  }

  public void setCoffee(char coffee) {
    this.coffee = coffee;
  }
}
