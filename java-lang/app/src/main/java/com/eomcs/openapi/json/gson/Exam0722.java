// JSON 문자열 --> 객체 : 다른 객체를 여러 개 포함하는 경우
package com.eomcs.openapi.json.gson;

import com.google.gson.Gson;

public class Exam0722 {
  public static void main(String[] args) {

    String jsonStr = "{\"no\":100,\"title\":\"Hello\",\"manager\":{\"position\":\"대리\",\"fax\":\"02-1111-2222\",\"no\":101,\"name\":\"홍길동\",\"email\":\"hong@test.com\",\"registeredDate\":\"10월 5, 2021\"}}";

    ManagerParam param = new Gson().fromJson(jsonStr, ManagerParam.class);

    System.out.println(param.no);
    System.out.println(param.title);
    System.out.println(param.manager);
  }
}
//맵으로 저장은 똑바로 했지만 맵으로 꺼낼땐 원하는 객체로 꺼내지지 않음.. 그래서 ,,, 
//Managaram.class에 담아라라고 똑바로 시키기




