package bitcamp.util;

public class MenuGroup extends Menu {

  ArrayList childs; // ArrayList menus = new ArrayList(); 초기화 수행문은 밑으로 내려감

  public MenuGroup(String title) {
    super(title);// 자바세계에서는 맨 먼저 수퍼 클래스의 생성자를 호출하는 코드를 넣어야하는데 안 넣으면 컴파일러가 자동으로 넣음
                 // 근데 만약 수퍼클래스 안에 생성자가 없으면 super(파라미터)로 명시적으로 써서 호출해야함.
    this.childs = new ArrayList();
  }

  public void add(Menu menu) {
    this.childs.add(menu);
  }

  @Override
  public void execute(BreadcrumbPrompt prompt) {

    prompt.appendBreadcrumb(this.getTitle());

    this.printMenu();
    while (true) {
      String input = prompt.inputMenu();
      if (input.equals("menu")) {
        this.printMenu();
        continue;
      }

      int menuNo = Integer.parseInt(input);
      if (menuNo < 0 || menuNo > childs.size()) {
        System.out.println("메뉴 번호가 옳지 않습니다.");
      } else if (menuNo == 0) {
        prompt.removeBreadcrumb();
        return;
      } else {
        Menu menu = (Menu) this.childs.get(menuNo - 1);
        menu.execute(prompt);
      }
    }
  }

  private void printMenu() {
    for (int i = 0; i < childs.size(); i++) {
      Menu menu = (Menu) childs.get(i);
      System.out.printf("%d, %s\n", i + 1, menu.getTitle());
    }
    System.out.println("0. 이전/종료");
  }
}
