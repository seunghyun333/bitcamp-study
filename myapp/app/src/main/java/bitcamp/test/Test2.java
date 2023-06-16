package bitcamp.test;

import bitcamp.myapp.vo.Member;

public class Test2 {
	public static void main(String[] args) {
		Member m1 = new Member();
		m1.setNo(1);
		m1.setName("홍길동");
		m1.setEmail("hong@test.com");
		m1.setPassword("1111");
		m1.setGender('M');

		Member m2 = new Member();
		m2.setNo(1);
		m2.setName("유관순");
		m2.setEmail("yoo@test.com");
		m2.setPassword("2222");
		m2.setGender('w');
		
		System.out.println(m1 == m2);
		//각 레퍼런스에 저장된 인스턴스 주소를 비교 
		System.out.println(m1.equals(m2));
		//equals() 수퍼 클래스 Object의 메서드이다.
		//Object의 equals()는 두 인스턴스가 같은지 비교한다.
		
		
		Object obj1 = m1;
		Object obj2 = m2;
		
		System.out.println(obj1.equals(obj2));
		//다형적 변수의 규칙 
		// => 컴파일이 통과되면, 
		// => JVM이 메서드를 호출할 때 
		// => 다형적 변수가 실제 가리키는 인스턴스의 클래스부터 찾아 올라간다. 
		
		//컴파일러 : obj1은 object 타입이고 object 안에 equals 있으니까 컴파일 ok 
		//jvm이 현 obj1 타입은 object지만 실제는 member의 객체 주소를 갖고 잇기때문에 member 클래스에서 equals를 찾는다 .
		
	}

}
