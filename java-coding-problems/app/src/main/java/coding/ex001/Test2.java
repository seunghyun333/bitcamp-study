package coding.ex001;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

public class Test2 {
  public static void main(String[] args) {
	
		
		// BiFunction : <T,U,R> 인터페이스
		// => R apply(T, U);
		
		// Map.put(키, 값)
		// 키: character type, 
		// 값: Integer 
		// 예: put('x', 100);// 값 직접 넣는것
		
		// Map.compute(키, 값을 리턴할 객체);
		// => 키: K 
		// => 값을 리턴할 객체: BiFunction<? super K, ? super V, ? extends V> 
		// => 예: compute('x', BiFunction 구현객체); / 값을 만드는 객체 사용해서 값을 넣음
		
		
		class MyValue implements BiFunction<Character, Integer, Integer> {
			//Character : 키, Integer: 값, Integer: 리턴타입임
		 @Override
		public Integer apply(Character key, Integer value) {
			// 이 메서드는 Map.compute()가 호출한다.
			 // 파라미터로 넘어오오는 것은 기존이 저장된 키와 값이다
			 //해당 키의 값이 없다면 null이 넘어온다. 
			 
			return  (value == null ) ? 1 : value +1;
		}
		}
		
		MyValue 값생성기 = new MyValue();
		
		String str = "Be strong, be fearless, be beautiful. "
				+ "And believe that anything is possible when you have the right "
				+ "people there to support you. ";
		
		Map<Character,Integer> result = new HashMap<>();
		
		for (char ch : str.toCharArray()) {
		result.compute(ch,값생성기);
	}
	
	for (Entry<Character,Integer> entry: result.entrySet()) {
		System.out.printf("%c: %d\n", entry.getKey(), entry.getValue());
	}
	
  }
}
