package bitcamp.personalapp.handler;


import java.io.IOException;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface DiaryActionListener extends ActionListener {

    
    static char inputCoffee(char coffee, BreadcrumbPrompt prompt) throws IOException {
      String label;
      if (coffee == 0) {
        label = "모닝커피? \n";
      } else {
        label = String.format("모닝커피(%s)?\n", coffee == 'O' ? "마심" : "안 마심");
      }

      while (true) {
        String select = prompt.inputString(label + 
        		" 1. 마심\n"+ 
        		" 2. 안 마심\n"+  
        		"> ");
      
        switch (select) {
            case "1":
                return Diary.DRINK;
            case "2":
                return Diary.NONCOFFEE;
            default:
                System.out.println("1, 2 中 선택");
            }
          }
    }
    }


