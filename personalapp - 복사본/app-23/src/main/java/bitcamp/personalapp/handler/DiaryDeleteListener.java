package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class DiaryDeleteListener extends AbstractDiaryListener {


    
    public DiaryDeleteListener(List list) {
    	super(list);
    }


    public void service(BreadcrumbPrompt prompt){
    	if(!this.list.remove(new Diary(prompt.inputInt("삭제할 번호?")))) {
    		System.out.println("무효한 번호입니다!! ");
    	}
      }
   
    }


