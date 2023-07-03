package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;

public abstract class AbstractVisitListener implements ActionListener {
	
	protected List<Visit> list;
	
	public AbstractVisitListener(List<Visit> list) {
		this.list = list;
	}
	
	protected Visit findBy(int no) {
		for(int i = 0; i < this.list.size(); i++) {
			Visit v = this.list.get(i);
			if(v.getNo() == no) {
				return v;
			}
		}
		return null;
	}

}
