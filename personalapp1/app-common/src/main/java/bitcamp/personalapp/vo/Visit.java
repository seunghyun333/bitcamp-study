package bitcamp.personalapp.vo;

import java.io.Serializable;

public class Visit implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private int no; 
	private String name;
	private long createdDate;
	
	public Visit() {}
	
	public Visit(int no) {
		this.no = no;
	}

	
	public boolean equals (Object obj) {
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Visit visit =(Visit) obj;
		if(this.getNo() != visit.getNo()) {
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
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
}



