package bitcamp.personalapp.dao;

import java.util.List;
import bitcamp.personalapp.vo.Visit;

public interface VisitDao {
  void insert(Visit visit);

  List<Visit> list();

  Visit findByIdAndPassword(Visit v);



}
