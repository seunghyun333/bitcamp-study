package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class DiaryUpdateListener extends AbstractDiaryListener {


    
    public DiaryUpdateListener(List list) {
    	super(list);
    }


    
    public void service(BreadcrumbPrompt prompt){
        int diaryNo = prompt.inputInt("수정할 일기 번호? ");
        
        Diary d = this.findBy(diaryNo);
        if (d == null) {
        	System.out.println("해당 번호의 일기가 없습니다. ");
        	return;
        }
        
        d.setDate(prompt.inputString("날짜(%s)? >", d.getDate()));
        d.setTitle(prompt.inputString("제목(%s)? >", d.getTitle()));
        d.setContents(prompt.inputString("내용(%s)? >", d.getContents()));
            d.setCoffee(inputCoffee(d.getCoffee(), prompt));
        }
      
    
 
   
    }


