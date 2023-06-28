package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class DiaryListListener extends AbstractDiaryListener{


    
    public DiaryListListener(List<Diary> list) {
    	super(list);
    }


    
    public void service(BreadcrumbPrompt prompt){
        System.out.println("--------------------------------------------");
        System.out.println("번호, 날짜, 날씨, 제목, 모닝커피");      
        System.out.println("--------------------------------------------");

        for (int i = 0; i < this.list.size(); i++) {
        	Diary diary = this.list.get(i);
          System.out.printf("%d, %s, %s, %s, %s\n", 
        diary.getNo(), diary.getDate(), diary.getWeather(), diary.getTitle(), toCoffeeString(diary.getCoffee()));
        }
      }  

 
   
    }


