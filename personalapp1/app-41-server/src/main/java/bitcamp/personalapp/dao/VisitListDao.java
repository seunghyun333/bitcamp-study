package bitcamp.personalapp.dao;

import java.util.ArrayList;
import java.util.List;

import bitcamp.personalapp.vo.Visit;
import bitcamp.util.JsonDataHelper;

public class VisitListDao implements VisitDao {
	
	String filename;
	ArrayList<Visit> list = new ArrayList<>();
	
	public VisitListDao(String filename) {
		this.filename = filename;
		JsonDataHelper.loadJson(filename, list, Visit.class);
	}
	
	  @Override
	  public List<Visit> list() {
	    return this.list;
	  }
	
	public void insert(Visit visit) {
		visit.setNo(Visit.VisitNo++);
		visit.setCreatedDate(System.currentTimeMillis());
		this.list.add(visit);
		JsonDataHelper.saveJson(filename, list);
	}


	}
