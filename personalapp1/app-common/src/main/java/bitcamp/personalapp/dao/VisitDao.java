package bitcamp.personalapp.dao;

import java.util.List;
import bitcamp.personalapp.vo.Visit;

public interface VisitDao {
  void insert(Visit visit);

  List<Visit> findAll();

  Visit findBy(int no);

  Visit findByIdAndPassword(Visit v);

  int delete(int no);



}
