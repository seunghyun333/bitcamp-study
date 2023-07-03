// 메서드 chaianing call
package com.eomcs.openapi.json.gson;

public class Exam0117 {
  public static void main(String[] args) {
	  
	  class Member {
		  int no;
		  String name; 
		  String email;
		  String password;
		  boolean working;
		public int getNo() {return no;}
		public void setNo(int no) {this.no = no;}
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		public String getEmail() {return email;}
		public void setEmail(String eamil) {this.email = eamil;}
		public String getPassword() {return password;}
		public void setPassword(String password) {this.password = password;}
		public boolean isWorking() {return working;}
		public void setWorking(boolean working) {this.working = working;}
		

		public Member No(int no) {this.no = no; return this;}
		public Member Name(String name) {this.name = name;return this;}
		public Member Email(String eamil) {this.email = eamil;return this;}
		public Member Password(String password) {this.password = password;return this;}
		public Member Working(boolean working) {this.working = working;return this;}
		
		// 요새 트렌드 ~ : 세터와 게터를 그대로 냅두고 체이닝콜을 위한 코드를 따로 준비하고 체이닝콜 함. 
		// 세터에 리턴 타입이 있으면 프로그램이 잘 못 알아 먹을 대도 있음,,? 
		
		
	  }
	  
	  Member m = new Member()
			  .No(100)
			  .Name("홍길동")
			  .Email("hong.naver.com")
			  .Password("1111")
			  .Working(true);
	  
	  //이렇게 하면 생성자 유형을 여러개 만들 필요 없음. 
	  //	아래와 같이 걍 쓰면 댐 
	  //	  Member m = new Member()
	  //	  .No(100)
	  //	  .Name("홍길동")
  }
  }



