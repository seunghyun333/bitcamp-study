package bitcamp.personalapp.handler;

import java.io.IOException;
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


	
	public void service(BreadcrumbPrompt prompt) throws IOException {
	    prompt.println("============================");
	    prompt.printf("ID번호, 방문자, 날짜\n");
	    prompt.println("============================");
	    
	   List<Visit> list = visitDao.list();
	    for (Visit visit : list) {
	      prompt.printf("%d, %s, %tY-%3$tm-%3$td\n", 
	    		  visit.getNo(),visit.getName(),visit.getCreatedDate());
	    }
	}
	}



