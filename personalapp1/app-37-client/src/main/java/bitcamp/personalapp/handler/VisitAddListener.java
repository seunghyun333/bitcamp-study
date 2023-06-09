package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class VisitAddListener implements ActionListener {
	
	VisitDao visitDao;
 
    
    public VisitAddListener(VisitDao visitDao) {
    	this.visitDao = visitDao;
    }


    public void service(BreadcrumbPrompt prompt) {
    	
      Visit visit = new Visit();
      
      visit.setNo(Visit.VisitNo++);
      visit.setName(prompt.inputString("이름을 적어주세요 ♡"));
      visit.setCreatedDate(System.currentTimeMillis());
      System.out.printf("%s아~ 고마워♡\n", visit.getName());

      visitDao.insert(visit);
    }

    }


