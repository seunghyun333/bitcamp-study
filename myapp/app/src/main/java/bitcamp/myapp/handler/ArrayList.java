package bitcamp.myapp.handler;

public class ArrayList {
  private static final int DEFAULT_SIZE = 3; 
  
  private Object[] list = new Object[DEFAULT_SIZE]; 
  private int length;
  
  
  public void add(Object obj) {
	if(this.length == list.length) {
		increase();
	} 
	this.list[this.length++] = obj;
  }
  
  private void increase() {
	  Object[] arr = new Object[list.length + (list.length >> 1)];
	  for(int i =0; i < list.length; i++) {
		  arr[i] = list[i];
	  }
	  list = arr;
	  	//System.out.println("배열확장:  " + list.length);
  }
  
  //복제해서 담아서 리턴
  public Object[] list() {
	  //리턴할 값을 담을 배열 생성
	  Object[] arr = new Object[this.length];
	  
	  //원본 배열에서 입력된 인스턴스 주소를 꺼내 새 배열에 담는다.
	  for (int i = 0; i < this.length; i++) {
	      arr[i] = this.list[i];
	    }
	  
	  //새 배열을 리턴한다.
	  return arr;
	  

  }
  
  public Object get(Object obj) {
	  for (int i = 0; i < this.length; i++) {
	      Object item = this.list[i];
	      if (item.equals(obj)) {     
	        return item;
	      }
	    }
	  return null;
  }
  
  public boolean delete(Object obj) {
    int delectedIndex = indexOf(obj);
	 if (delectedIndex == -1) {
	    return false;
	    }
	 
   for (int i = delectedIndex; i < this.length - 1; i++) {
	 this.list[i] = this.list[i + 1];
	}
   this.list[--this.length] = null;	  
   return true;
  }
  
  private int indexOf(Object obj) {
	    for (int i = 0; i < this.length; i++) {
	      Object item = this.list[i];
	      if (item.equals(obj)) {
	        return i;
	      }
	    }
	    return -1;
	  }
  
  private boolean available() {
	return this.length < DEFAULT_SIZE; 
	} // 외부에서 import 하지 않는 메서드이기 때문에 public 취소하기, private 붙이면 이 클래스 안에서만 쓸 수 있음
  
}
