package bitcamp.personalapp.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.JsonDataHelper;

public class DiaryListDao implements DiaryDao {

  String filename;
  ArrayList<Diary> list = new ArrayList<>();

  public DiaryListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Diary.class);
  }

  @Override
  public List<Diary> list() {
    return this.list;
  }

  @Override
  public void insert(Diary diary) {
    diary.setNo(Diary.turn++);
    this.list.add(diary);
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public Diary findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Diary m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public int update(Diary diary) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == diary.getNo()) {
        list.set(i, diary);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }



}
