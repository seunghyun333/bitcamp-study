package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryDeleteListener extends AbstractDiaryListener {


    
    public DiaryDeleteListener(List<Diary> list) {
    	super(list);
    }


    public void service(BreadcrumbPrompt prompt){
    	if(!this.list.remove(new Diary(prompt.inputInt("삭제할 번호?")))) {
    		System.out.println("무효한 번호입니다!! ");
    	}
      }
   
    }


