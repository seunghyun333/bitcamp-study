package bitcamp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Stateful 방식으로 통신하기 
public class CalcClient1 {
	
	static Pattern pattern = Pattern.compile("[0-9]+|\\p{Punct}"); // 0부터 9까지 숫자 집합 중 하나, +는 1개 이상이라는 뜻 또는 연산 집합 중 1개 
	
	public static void main(String[] args) {
		try(
		Socket socket = new Socket("localhost", 8888);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		Scanner keyscan = new Scanner(System.in)) {
		
	while(true) {
	System.out.print("계산식(예: +3)> ");		 
	String input = keyscan.nextLine();
	if (input.equals("quit")){
		out.writeUTF("quit");
		break;
	}
	
	try {
	Expression expr = parseExpression(input);

	out.writeUTF(expr.op);
	out.writeInt(expr.value);
	
	String result = in.readUTF();
	System.out.printf("결과: %s\n", result);
	} catch (ExpressionParseException e) {
		System.out.println("계산식이 옳지 않습니다.");
	  }
	 }
	} catch (IOException e) {
		System.out.println("서버 통신오류");
	
   }
  } 
	
	public static Expression parseExpression(String expr) throws ExpressionParseException {
		try {
		Matcher matcher = pattern.matcher(expr);
		
		ArrayList<String> values = new ArrayList<>();
		while (matcher.find()) {
			values.add(matcher.group());
		}
		
		if(values.size() != 2) {
			throw new Exception("계산식이 옳지 않습니다.");
		}
		
		Expression obj =new Expression();
		obj.op = values.get(0);
		obj.value= Integer.parseInt(values.get(1));
		
		return obj;
	} catch (Exception e) {
		throw new ExpressionParseException(e); //Exception 상속 받은 클래스 : 예외 클래스. 아무것도 안 한 메서드지만 ,, 어떤 이유로 예외가 발생했는지 알 수 있어서 사용 
	}
	} 
	
	static class Expression {
		String op;
		int value;
		
	}
}
