package bitcamp.personalapp;

import bitcamp.util.Prompt;
import bitcamp.personalapp.handler.DiaryHandler;

public class App {

  public static void main(String[]args){
    
    printTitle();

    while (DiaryHandler.available()) {
      DiaryHandler.inputContents();
      if (!promptContinue()){
        break;
      }
    }

    DiaryHandler.printTotal();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");   
  }

 
  static boolean promptContinue() {
    String response = Prompt.inputString("계속 입력하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")){
        return false;
      }
    return true;
    }
  }
