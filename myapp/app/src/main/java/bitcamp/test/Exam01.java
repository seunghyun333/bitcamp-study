package bitcamp.test;

class A {
	int v1 =100;
	int v2=200;
	
	{
		System.out.println("111");	
		System.out.println("222");	
		System.out.println("333");	
	}
	
	public A(int a) {
		int v1 =100;
		int v2=200;
		System.out.println("444");	
	}
	
	public A(int a, int b) {
		int v1 =100;
		int v2=200;
		System.out.println("555");	
	}
	
	public A(int a, int b, int c) {
		int v1 =100;
		int v2=200;
		System.out.println("666");	
	}
	

}

public class Exam01 {
	public static void main(String[] args) {
		new A(1);
		new A(2,3);
		new A(2,3,4);
	}
}


