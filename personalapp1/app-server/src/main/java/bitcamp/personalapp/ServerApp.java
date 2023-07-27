package bitcamp.personalapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.net.NetProtocol;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.MySQLBoardDao;
import bitcamp.personalapp.dao.MySQLDiaryDao;
import bitcamp.personalapp.dao.MySQLVisitDao;
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
import bitcamp.personalapp.handler.LoginListener;
import bitcamp.personalapp.handler.VisitAddListener;
import bitcamp.personalapp.handler.VisitListListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;
import bitcamp.util.SqlSessionFactoryProxy;

public class ServerApp {

  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  SqlSessionFactory sqlSessionFactory;
  DataSource ds = new DataSource("jdbc:mysql://localhost:3306/studydb", "study", "1111");
  DiaryDao diaryDao;
  BoardDao boardDao;
  VisitDao visitDao;

  MenuGroup mainMenu = new MenuGroup("메인");
  
  int port;

  public ServerApp(int port) throws Exception {
	  
	this.port = port;
	
	InputStream mybatisConfigIn = Resources.getResourceAsStream("bitcamp/personalapp/config/mybatis-config.xml");
	
	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	
	sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    this.diaryDao = new MySQLDiaryDao(ds);
    this.boardDao = new MySQLBoardDao(sqlSessionFactory, ds);
    this.visitDao = new MySQLVisitDao(ds);

    prepareMenu();
  }

  public void close() throws Exception {
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }



  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
    	System.out.println("서버 실행 중...");
    	
    	while (true) {
    		Socket socket = serverSocket.accept();
    		threadPool.execute(()-> processRequest(socket));
    	}
    } catch (Exception e) {
    	System.out.println("서버 실행 오류!");
    	e.printStackTrace();
    }
  }
  
  private void processRequest(Socket socket) {
	  try (Socket s = socket;
		  DataInputStream in = new DataInputStream(socket.getInputStream());
		  DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
		  
		BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);
		
		InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());
		
		out.writeUTF("[나의 다이어리 관리 시스템]\n"
				+ "-------------------------------------");
		
		new LoginListener(visitDao).service(prompt);
		
		mainMenu.execute(prompt);
		out.writeUTF(NetProtocol.NET_END);
	  } catch (Exception e) {
		  System.out.println("클라이언트 통신 오류!");
		  e.printStackTrace();
	  } finally {
		  ds.clean();
	  }
  }


  private void prepareMenu() {
    MenuGroup diaryMenu = new MenuGroup("오늘의 일기");
    diaryMenu.add(new Menu("등록", new DiaryAddListener(diaryDao, ds)));
    diaryMenu.add(new Menu("목록", new DiaryListListener(diaryDao)));
    diaryMenu.add(new Menu("조회", new DiaryDetailListener(diaryDao)));
    diaryMenu.add(new Menu("변경", new DiaryUpdateListener(diaryDao, ds)));
    diaryMenu.add(new Menu("삭제", new DiaryDeleteListener(diaryDao, ds)));
    mainMenu.add(diaryMenu);

    MenuGroup boardMenu = new MenuGroup("응원의 한마디");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao, ds)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao, ds)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao, ds)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao, ds)));
    mainMenu.add(boardMenu);


    MenuGroup manageMenu = new MenuGroup("방명록");
    manageMenu.add(new Menu("이름을 적어주세요", new VisitAddListener(visitDao, ds)));
    manageMenu.add(new Menu("방문자", new VisitListListener(visitDao)));
    mainMenu.add(manageMenu);

  }
}
