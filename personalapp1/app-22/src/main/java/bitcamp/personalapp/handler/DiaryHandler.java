package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.List;
import bitcamp.util.MenuPrompt;

public class DiaryHandler implements Handler {

    private List list;
    private MenuPrompt prompt;
    private String title;
    
    public DiaryHandler(MenuPrompt prompt, String title, List list) {
      this.prompt = prompt;
      this.title = title;
      this.list = list;
    }

    public void excute() {
    	prompt.appendBreadcrumb(this.title, getMenu());
    	prompt.printMenu();
        
      while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
      case "0" : prompt.removeBreadcrumb(); return;
      case "1" : this.inputContents(); break;
      case "2" : this.printDiary(); break;
      case "3" : this.viewDiary(); break;
      case "4" : this.updateDiary(); break;
      case "5" : this.deleteDiary(); break;
      } 
    }
    }

  private static String getMenu() {
	  StringBuilder menu = new StringBuilder();
    menu.append("1. 일기 등록\n");
    menu.append("2. 일기 목록\n");
    menu.append("3. 일기 조회\n");
    menu.append("4. 일기 변경\n");
    menu.append("5. 일기 삭제\n");
    menu.append("0. 메뉴\n");
    return menu.toString();
  }

    public void inputContents() {
    	
      Diary diary = new Diary();
      diary.setDate(this.prompt.inputString("날짜 ? "));
      diary.setWeather(this.prompt.inputString("날씨 ? "));
      diary.setTitle(this.prompt.inputString("제목 ? "));
      diary.setContents(this.prompt.inputString("내용 ? "));
      diary.setCoffee(inputCoffee((char) 0));

      this.list.add(diary);
    }
    
    public void printDiary(){
        System.out.println("--------------------------------------------");
        System.out.println("번호, 날짜, 날씨, 제목, 모닝커피");      
        System.out.println("--------------------------------------------");

        for (int i = 0; i < this.list.size(); i++) {
        	Diary diary = (Diary) this.list.get(i);
          System.out.printf("%d, %s, %s, %s, %s\n", 
        diary.getNo(), diary.getDate(), diary.getWeather(), diary.getTitle(), toCoffeeString(diary.getCoffee()));
        }
      }  

    public void viewDiary() {
        int diaryNo = this.prompt.inputInt("일기 번호? ");
        
        Diary diary = this.findBy(diaryNo);
        if(diary == null) {
            System.out.println("해당 번호의 일기가 없습니다.");
            return;
        }

            System.out.printf("날짜 : %s \n", diary.getDate());
            System.out.printf("날씨 : %s \n", diary.getWeather());
            System.out.printf("제목 : %s \n", diary.getTitle());
            System.out.printf("내용 : %s \n", diary.getContents());
            System.out.printf("모닝커피 : %s \n", toCoffeeString(diary.getCoffee()));
          }
        
    
    public void updateDiary(){
        int diaryNo = this.prompt.inputInt("수정할 일기 번호? ");
        
        Diary diary = this.findBy(diaryNo);
        if (diary == null) {
        	System.out.println("해당 번호의 일기가 없습니다. ");
        	return;
        }
            System.out.printf("날짜(%s)? >", diary.getDate());
            diary.setDate(this.prompt.inputString(""));
            System.out.printf("제목(%s)? >", diary.getTitle());
            diary.setTitle(this.prompt.inputString(""));
            System.out.printf("내용(%s)? >", diary.getContents());
            diary.setContents(this.prompt.inputString(""));
            diary.setCoffee(inputCoffee(diary.getCoffee()));
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
    	if(!this.list.remove(new Diary(this.prompt.inputInt("삭제할 번호?")))) {
    		System.out.println("무효한 번호입니다!! ");
    	}
      }
    
    private Diary findBy(int no) {
    	for(int i=0; i < this.list.size(); i++) {
    		Diary d = (Diary) this.list.get(i);
    		if(d.getNo() == no) {
    			return d;
    		}
    	}
    	return null;
    }
    }


