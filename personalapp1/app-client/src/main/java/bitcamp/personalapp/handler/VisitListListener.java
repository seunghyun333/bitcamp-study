package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class VisitListListener implements ActionListener {
	
	VisitDao visitDao;

	public VisitListListener(VisitDao visitDao) {
		this.visitDao = visitDao;
	}


	
	public void service(BreadcrumbPrompt prompt) {
	    System.out.println("============================");
	    System.out.printf("번호, 방문자, 날짜\n");
	    System.out.println("============================");
	    
	   List<Visit> list = visitDao.list();
	    for (Visit visit : list) {
	      System.out.printf("%d, %s, %tY-%3$tm-%3$td\n", 
	    		  visit.getNo(),visit.getName(),visit.getCreatedDate());
	    }
	}
	}



