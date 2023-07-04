package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.vo.Visit;
import bitcamp.util.BreadcrumbPrompt;

public class VisitAddListener extends AbstractVisitListener {


    
    public VisitAddListener(List<Visit> list) {
    	super(list);
    }


    public void service(BreadcrumbPrompt prompt) {
    	
      Visit visit = new Visit();
      
      visit.setNo(Visit.VisitNo++);
      visit.setName(prompt.inputString("이름을 적어주세요 ♡"));
      visit.setCreatedDate(System.currentTimeMillis());
      System.out.printf("%s아~ 고마워♡\n", visit.getName());

      this.list.add(visit);
    }

    }


