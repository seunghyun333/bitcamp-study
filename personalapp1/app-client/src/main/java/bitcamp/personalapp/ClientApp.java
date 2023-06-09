package bitcamp.personalapp;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.DaoBuilder;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.handler.BoardAddListener;
import bitcamp.personalapp.handler.BoardDeleteListener;
import bitcamp.personalapp.handler.BoardDetailListener;
import bitcamp.personalapp.handler.BoardListListener;
import bitcamp.personalapp.handler.BoardUpdateListener;
import bitcamp.personalapp.handler.DiaryAddListener;
import bitcamp.personalapp.handler.DiaryDeleteListener;
import bitcamp.personalapp.handler.DiaryDetailListener;
import bitcamp.personalapp.handler.DiaryListListener;
import bitcamp.personalapp.handler.DiaryUpdateListener;
import bitcamp.personalapp.handler.VisitAddListener;
import bitcamp.personalapp.handler.VisitListListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
 
public class ClientApp {

  DiaryDao diaryDao;
  BoardDao boardDao;
  VisitDao visitDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {

    DaoBuilder daoBuilder = new DaoBuilder(ip, port);

    this.diaryDao = daoBuilder.build("diary", DiaryDao.class);
    this.boardDao = daoBuilder.build("board", BoardDao.class);
    this.visitDao = daoBuilder.build("visit", VisitDao.class);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... bitcamp.myapp.ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("나의 개발자 도전기");
    System.out.println("-------------------------");
  }

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);
  }


  private void prepareMenu() {
    MenuGroup diaryMenu = new MenuGroup("오늘의 일기");
    diaryMenu.add(new Menu("등록", new DiaryAddListener(diaryDao)));
    diaryMenu.add(new Menu("목록", new DiaryListListener(diaryDao)));
    diaryMenu.add(new Menu("조회", new DiaryDetailListener(diaryDao)));
    diaryMenu.add(new Menu("변경", new DiaryUpdateListener(diaryDao)));
    diaryMenu.add(new Menu("삭제", new DiaryDeleteListener(diaryDao)));
    mainMenu.add(diaryMenu);

    MenuGroup boardMenu = new MenuGroup("응원의 한마디");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);


    MenuGroup manageMenu = new MenuGroup("방명록");
    manageMenu.add(new Menu("이름을 적어주세요", new VisitAddListener(visitDao)));
    manageMenu.add(new Menu("방문자", new VisitListListener(visitDao)));
    mainMenu.add(manageMenu);

  }
}
