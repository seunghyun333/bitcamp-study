package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.vo.Visit;
import bitcamp.util.BreadcrumbPrompt;

public class VisitListListener extends AbstractVisitListener{


	public VisitListListener(List<Visit> list) {
		super(list);
	}


	
	public void service(BreadcrumbPrompt prompt) {
	    System.out.println("============================");
	    System.out.printf("번호, 방문자, 날짜\n");
	    System.out.println("============================");
	    
	   
	    for (int i = 0; i < this.list.size(); i++) {
	    	Visit visit = this.list.get(i);
	      System.out.printf("%d, %s, %tY-%5$tm-%5$td\n", 
	    		  visit.getNo(),visit.getName(),visit.getCreatedDate());
	    }
	}


	}



