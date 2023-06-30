package bitcamp.personalapp.vo;

import java.io.Serializable;

public class Diary implements Serializable, CsvObject{
  private static final long serialVersionUID = 1L;

  public static int turn = 1;

  public static final char DRINK = 'O';
  public static final char NONCOFFEE = 'X';

  public int no;
  public String date;
  public String title;
  public String weather;
  public String contents;
  public char coffee;

  public Diary() {
    this.no = turn++;
  }
  
  public Diary(int no) {
	  this.no = no;
  }
  
  public static Diary fromCsv(String csv) {
	  String[] values = csv.split(",");
	  
	  Diary diary = new Diary(Integer.parseInt(values[0]));
	  diary.setDate(values[1]);
	  diary.setTitle(values[2]);
	  diary.setWeather(values[3]);
	  diary.setContents(values[4]);
	  diary.setCoffee(values[5].charAt(0));
	  
	  if(Diary.turn <= diary.getNo()) {
		  Diary.turn = diary.getNo() +1;
	  }
	  
	  return diary;
	  
  }
  
  @Override
  public String toCsvString() {
	  return String.format("%d,%s,%s,%s,%s,%c",
				this.getNo(),
				this.getDate(),
				this.getTitle(),
				this.getWeather(),
				this.getContents(),
				this.getCoffee());
			  
  }
  
  public boolean equals (Object obj) {
	  if(obj == null) {
		  return false;
	  }
	  if(this.getClass() != obj.getClass() ) {
		  return false;
	  }
	  
	  Diary diary = (Diary) obj;
	  
	  if(this.getNo() != diary.getNo() ) {
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

public String getContents() {
	return contents;
}

public void setContents(String contents) {
	this.contents = contents;
}

public char getCoffee() {
	return coffee;
}

public void setCoffee(char coffee) {
	this.coffee = coffee;
}
}
