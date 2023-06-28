package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public abstract class AbstractDiaryListener implements ActionListener {

    protected List list;

    
    public AbstractDiaryListener(List list) {
      this.list = list;
    }

 
    
    public static String toCoffeeString(char coffee) {
      return coffee == 'O' ? "마심" : "안 마심";
    }

    protected Diary findBy(int no) {
    	for(int i=0; i < this.list.size(); i++) {
    		Diary d = (Diary) this.list.get(i);
    		if(d.getNo() == no) {
    			return d;
    		}
    	}
    	return null;
    }
    
    protected char inputCoffee(char coffee, BreadcrumbPrompt prompt) {
      String label;
      if (coffee == 0) {
        label = "모닝커피? \n";
      } else {
        label = String.format("모닝커피(%s)?\n", toCoffeeString(coffee));
      }

      while (true) {
        String select = prompt.inputString(label + " 1. 마심\n"+ " 2. 안 마심\n"+  "> ");
      
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


    }


