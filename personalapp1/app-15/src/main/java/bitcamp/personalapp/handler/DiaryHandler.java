package bitcamp.personalapp.handler;

import bitcamp.util.Prompt;
import bitcamp.personalapp.vo.Diary;

public class DiaryHandler {

    private final int MAX_SIZE = 100;
    
    private Prompt prompt;
    private Diary[] diarys = new Diary[MAX_SIZE];
    private int length;
    
    public DiaryHandler(Prompt prompt) {
    	this.prompt = prompt;
    }

    public void inputContents() {
      if (!available()) {
        System.out.println("더 이상 입력할 수 없습니다!");
        return;
      }
      Diary diary = new Diary();
      diary.setDate(this.prompt.inputString("날짜 ? "));
      diary.setWeather(this.prompt.inputString("날씨 ? "));
      diary.setTitle(this.prompt.inputString("제목 ? "));
      diary.setContents(this.prompt.inputString("내용 ? "));
      diary.setCoffee(inputCoffee((char) 0));

      this.diarys[this.length++] = diary;
    }
    
    public void printDiary(){
        System.out.println("--------------------------------------------");
        System.out.println("번호, 날짜, 날씨, 제목, 모닝커피");      
        System.out.println("--------------------------------------------");

        for (int i = 0; i < this.length; i++) {
          Diary diary = diarys[i];
          System.out.printf("%d, %s, %s, %s, %s\n", 
        diary.getNo(), diary.getDate(), diary.getWeather(), diary.getTitle(), toCoffeeString(diary.getCoffee()));
        }
      }  

    public void viewDiary() {
        String diaryNo = this.prompt.inputString("일기번호? ");
        for (int i = 0; i < length; i++) {
          Diary diary = diarys[i];
          if (diary.getNo() == Integer.parseInt(diaryNo)) {
            System.out.printf("날짜 : %s \n", diary.getDate());
            System.out.printf("날씨 : %s \n", diary.getWeather());
            System.out.printf("제목 : %s \n", diary.getTitle());
            System.out.printf("내용 : %s \n", diary.getContents());
            System.out.printf("모닝커피 : %s \n", toCoffeeString(diary.getCoffee()));
            return;
          }
        }
        System.out.println("해당 번호의 일기가 없습니다.");
      }
    
    public void updateDiary(){
        String diaryNo = this.prompt.inputString("수정할 일기 번호? ");
        for (int i = 0; i < length; i++){
          Diary diary = diarys[i];
          if (diary.getNo() == Integer.parseInt(diaryNo)) {
            System.out.printf("날짜(%s)? >", diary.getDate());
            diary.setDate(this.prompt.inputString(""));
            System.out.printf("날씨(%s)? >", diary.getWeather());
            diary.setWeather(this.prompt.inputString(""));
            System.out.printf("제목(%s)? >", diary.getTitle());
            diary.setTitle(this.prompt.inputString(""));
            System.out.printf("내용(%s)? >", diary.getContents());
            diary.setContents(this.prompt.inputString(""));
            diary.setCoffee(inputCoffee(diary.getCoffee()));
            return;
          }
        }
        System.out.println("해당 번호의 일기가 없습니다. ");
      }
    
    public static String toCoffeeString(char coffee) {
      return coffee == 'O' ? "마심" : "안 마심";
    }

    private char inputCoffee(char coffee) {
      String label;
      if (coffee == 0) {
        label = "모닝커피? \n";
      } else {
        label = String.format("모닝커피(%s)?\n", toCoffeeString(coffee));
      }

      while (true) {
        String select = this.prompt.inputString(label + " 1. 마심\n"+ " 2. 안 마심\n"+  "> ");
      
        switch (select) {
            case "1":
                return Diary.DRINK;
            case "2":
                return Diary.NONCOFFEE;
            default:
                System.out.println("1, 2 中 선택");
            }
          }
    }

    public void deleteDiary(){
      int diaryNo = this.prompt.inputInt("삭제할 번호?") ;

      int deletedDiary = indexOf(diaryNo);
      if (deletedDiary == -1) {
        System.out.println("무효한 번호입니다.");
        return;
      }

      for (int i = deletedDiary; i < length -1; i++) {
        this.diarys[i] = this.diarys[i + 1];
      }

      this.diarys[--this.length] = null;
    }


    private int indexOf(int diaryNo) {
      for (int i = 0; i < this.length; i++){
        Diary diary = this.diarys[i];
        if (diary.no == diaryNo){
          return i;
        }
      }
      return -1;
    }

    private boolean available() {
      return this.length < MAX_SIZE;
  }
  }
