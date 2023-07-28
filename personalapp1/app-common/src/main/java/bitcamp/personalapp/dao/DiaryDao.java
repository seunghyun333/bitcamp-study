package bitcamp.personalapp.dao;

import java.util.List;

import bitcamp.personalapp.vo.Diary;

public interface DiaryDao {
	void insert(Diary diary);
	List<Diary> findAll();
	Diary findBy(int no);
	int update(Diary diary);
	int delete(int no);

}
