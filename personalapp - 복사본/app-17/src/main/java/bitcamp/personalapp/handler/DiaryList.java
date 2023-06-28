package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;

public class DiaryList {

    private final int MAX_SIZE = 100;
    private Diary[] diarys = new Diary[MAX_SIZE];
    private int length;  
    
    public void add(Diary diary) {
    	if (this.length == diarys.length) {
    		increase();
    	}
    	
    this.diarys[this.length++] = diary;	
    }
    
    private void increase() {
    	Diary[] arr = new Diary[diarys.length + (diarys.length >> 1)];
    	
    	for(int i = 0; i < diarys.length; i++) {
    		arr[i] = diarys[i];
    	}
    	diarys = arr;
    	//System.out.println("배열확장: " + diarys.length);
    }
    
    public Diary[] list() {
    	Diary[] arr = new Diary[this.length];
    	
        for (int i = 0; i < this.length; i++) {
            arr[i] = this.diarys[i];
    }
        return arr;
}
    
    public Diary get(int no) {
        for (int i = 0; i < this.length; i++) {
            Diary diary = this.diarys[i];
            if (diary.getNo() == no) {
            	return diary;
            }
    }
        return null;
}
    public boolean delete(int no) {
    	int delectedIndex = indexOf(no);
    	if (delectedIndex == -1) {
    		return false;
    	}
        for (int i = delectedIndex; i < length -1; i++) {
            this.diarys[i] = this.diarys[i + 1];
          }
          this.diarys[--this.length] = null;
          return true;
    }
    
    private int indexOf(int diaryNo) {
        for (int i = 0; i < this.length; i++){
          Diary diary = this.diarys[i];
          if (diary.getNo() == diaryNo){
            return i;
          }
        }
        return -1;
      }

}


